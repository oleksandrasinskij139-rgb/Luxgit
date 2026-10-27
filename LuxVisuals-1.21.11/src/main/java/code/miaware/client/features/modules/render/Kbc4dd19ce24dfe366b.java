package code.miaware.client.features.modules.render;

final class Kbc4dd19ce24dfe366b {
   int majorSeg;
   int minorSeg;
   float[] majorCos;
   float[] majorSin;
   float[] minorCos;
   float[] minorSin;

   private Kbc4dd19ce24dfe366b() {
   }

   void reset() {
      this.majorSeg = 0;
      this.minorSeg = 0;
      this.majorCos = null;
      this.majorSin = null;
      this.minorCos = null;
      this.minorSin = null;
   }

   void ensure(int major, int minor) {
      if (major != this.majorSeg || minor != this.minorSeg || this.majorCos == null || this.minorCos == null) {
         this.majorSeg = Math.max(12, major);
         this.minorSeg = Math.max(8, minor);
         this.majorCos = new float[this.majorSeg];
         this.majorSin = new float[this.majorSeg];
         this.minorCos = new float[this.minorSeg];
         this.minorSin = new float[this.minorSeg];

         for(int i = 0; i < this.majorSeg; ++i) {
            double a = (double)i / (double)this.majorSeg * (Math.PI * 2D);
            this.majorCos[i] = (float)Math.cos(a);
            this.majorSin[i] = (float)Math.sin(a);
         }

         for(int j = 0; j < this.minorSeg; ++j) {
            double b = (double)j / (double)this.minorSeg * (Math.PI * 2D);
            this.minorCos[j] = (float)Math.cos(b);
            this.minorSin[j] = (float)Math.sin(b);
         }

      }
   }
}
