package dev.alexandrevieira.sm.apis;

public class FinnhubQuote {
		
		//Open price of the day
		private double o;
		

		//High price of the day
		private double h;
		
		//Low price of the day
		private double l;

		//Current price
		private double c;

		//Previous close price
		private double pc;
		
		//Quote timestamp
		private long t;

		public double getOpenPrice() {
			return o;
		}

		public double getHighPrice() {
			return h;
		}

		public double getLowPrice() {
			return l;
		}

		public double getCurrentPrice() {
			return c;
		}

		public double getPreviousClosePrice() {
			return pc;
		}

		public long getTimestamp() {
			return t;
		}

		public void setO(double o) {
			this.o = o;
		}

		public void setH(double h) {
			this.h = h;
		}

		public void setL(double l) {
			this.l = l;
		}

		public void setC(double c) {
			this.c = c;
		}

		public void setPc(double pc) {
			this.pc = pc;
		}

		public void setT(long t) {
			this.t = t;
		}
		
		
	}