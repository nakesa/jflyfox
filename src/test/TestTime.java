package test;


public class TestTime {

	public static void main(String[] args) {
		int time = 944643;
		int tHour = 0, tMin = 0, tSec = 0, tDay = 0;
		if (time > 0) {
			tDay = (int) Math.floor(time / 60 / 60 / 24);
			tHour = (int) Math.floor(time / 60 / 60 % 24);
			tMin = (int) Math.floor(time / 60 % 60);
			tSec = time % 60;
		}
		System.out.println(tDay + "天" + tHour + "时" + tMin + "分" + tSec + "秒");
	}

}
