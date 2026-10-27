#!/usr/bin/env python3
"""Rewrite intermediary names (class_123, method_456, field_789, comp_12) in decompiled .java to yarn names.
Usage: remap_sources.py <tiny-v2 intermediary->named mapping> <dir> [<dir>...]
Get the mapping for the OLD version (1.21.4) - this only de-obfuscates names. Do the version bump (migrateMappings) afterwards.
  e.g. unzip yarn-1.21.4+build.8-v2.jar -> mappings/mappings.tiny   (from maven.fabricmc.net/net/fabricmc/yarn/)
  needs intermediary in column 1, named in column 2 (yarn v2 jar has exactly that after 'intermediary named' header)."""
import sys,re,os
def load(path):
    cls={};mem={}
    cur=None
    for line in open(path,encoding='utf8'):
        line=line.rstrip('\n')
        if line.startswith('tiny'): hdr=line.split('\t'); continue
        if line.startswith('c\t'):
            a=line.split('\t'); cur=a[1]; cls[a[1]]=a[2] if len(a)>2 and a[2] else a[1]
        elif line.startswith('\t') and not line.startswith('\t\t'):
            a=line.split('\t')   # '', m/f/c, desc, inter, named
            if a[1] in('m','f') and len(a)>=5 and a[4]: mem[a[3]]=a[4]
        elif line.startswith('\t\t'):
            a=line.split('\t')
            if len(a)>=5 and a[2]=='c' and a[3] and a[4]: mem[a[3]]=a[4]  # record components (comp_)
    return cls,mem
def main():
    cls,mem=load(sys.argv[1])
    short={}
    for i,n in cls.items():
        short[i.split('/')[-1]]=n.replace('/','.').replace('$','.')  # class_1796$class_1797 handled below
    tok=re.compile(r'\b(?:net\.minecraft\.)?(class_\d+(?:\$class_\d+)*)\b|\b((?:method|field|comp)_\d+)\b')
    def sub(m):
        if m.group(1):
            full='net/minecraft/'+m.group(1)
            n=cls.get(full)
            if not n: return m.group(0)
            dotted=n.replace('/','.').replace('$','.')
            return dotted if m.group(0).startswith('net.minecraft.') else dotted.split('.')[-1] if '$' not in n else '.'.join(dotted.split('.')[-2:])
        return mem.get(m.group(2),m.group(2))
    imp=re.compile(r'^(import (?:static )?)net\.minecraft\.(class_\d+(?:\$class_\d+)*)',re.M)
    n=0
    for root in sys.argv[2:]:
        for dp,_,fs in os.walk(root):
            for f in fs:
                if not f.endswith('.java'): continue
                p=os.path.join(dp,f); t=open(p,encoding='utf8',errors='ignore').read()
                def isub(m):
                    nm=cls.get('net/minecraft/'+m.group(2))
                    return m.group(1)+(nm.replace('/','.').replace('$','.') if nm else 'net.minecraft.'+m.group(2))
                t2=imp.sub(isub,t); t2=tok.sub(sub,t2)
                if t2!=t: open(p,'w',encoding='utf8').write(t2); n+=1
    print('rewrote',n,'files')
main()
