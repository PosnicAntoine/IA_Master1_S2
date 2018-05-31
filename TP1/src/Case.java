/*
 * TP 1 - IA Informatique : Sudoku
 * 
 * @author Tassadit BOUADI.
 */

public class Case {
	private int _i;
	private int _j;
	private int _val;
	
	
	public Case(int n){
		_i = 0;
		_j = 0;
		_val = 0;
	}
	
	public Case(int i, int j, int v, int n){
		_i = i;
		_j = j;
		_val = v;
	}
	
	public Case(int i, int j, int n){
		_i = i;
		_j = j;
		_val = 0;
	}
	
	public Case(Case c){
		_i = c._i;
		_j = c._j;
		_val = c._val;
	}
	
	
	public int getI(){
		return _i;
	}
	
	public int getJ(){
		return _j;
	}
	
	public int getVal(){
		return _val;
	}
	
	public void setVal(int v){
		_val = v;
	}
	
	
	public String toString(){
		return "[" + _i + "," + _j + "] " + _val;
	}
	
}
