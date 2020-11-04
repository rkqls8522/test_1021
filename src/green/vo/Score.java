package green.vo;

public class Score {
	protected int num;
	protected String name;
	protected int kor;
	protected int eng;
	protected int math;
	protected int sum;
	protected float avg;
	protected String grade;
	
	public int getNum() {
		return num;
	}
	public Score setNum(int num) {
		this.num = num;
		return this;
	}
	public String getName() {
		return name;
	}
	public Score setName(String name) {
		this.name = name;
		return this;
	}
	public int getSum() {
		return sum;
	}
	public Score setSum(int sum) {
		this.sum = sum;
		return this;
	}
	public float getAvg() {
		return avg;
	}
	public Score setAvg(float avg) {
		this.avg = avg;
		return this;
	}
	public String getGrade() {
		return grade;
	}
	public Score setGrade(String grade) {
		this.grade = grade;
		return this;
	}
	
	public int getKor() {
		return kor;
	}
	public Score setKor(int kor) {
		this.kor = kor;
		return this;
	}
	public int getEng() {
		return eng;
	}
	public Score setEng(int eng) {
		this.eng = eng;
		return this;
	}
	public int getMath() {
		return math;
	}
	public Score setMath(int math) {
		this.math = math;
		return this;
	}
	
	
}
