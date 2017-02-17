package chapter_5.session_1;

public class Alphabet {
	public static final Alphabet BINARY = new Alphabet("01");
	public static final Alphabet DECIMAL = new Alphabet("0123456789");
	public static final Alphabet HEXADECIMAL = new Alphabet("0123456789ABCDEF");
	public static final Alphabet DNA = new Alphabet("ACGT");
	public static final Alphabet LOWERCASE = new Alphabet("abcdefghijklmnopqrstuvwxyz");
	public static final Alphabet UPPERCASE = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
	public static final Alphabet BASE64 = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
	public static final Alphabet ASCII = new Alphabet(128);
	public static final Alphabet EXTENDED_ASCII = new Alphabet(256);
	
	private char[] alphabet;
	private int[] inverse;
	private final int R;
	
	public Alphabet(String alpha){
		boolean[] unicode = new boolean[Character.MAX_VALUE];
		for (int i = 0; i < alpha.length(); i++){
			char c = alpha.charAt(i);
			if (unicode[c])
				throw new IllegalArgumentException("Illegal alphabet: repeated character = '" + c + "'");
			unicode[c] = true;
		}
		alphabet = alpha.toCharArray();
		R = alpha.length();
		inverse = new int[Character.MAX_VALUE];
		for (int i = 0; i < inverse.length; i++){
			inverse[i] = -1;
		}
		for (int c = 0; c < R; c++){
			inverse[alphabet[c]] = c;
		}
	}
	private Alphabet(int radix){
		this.R = radix;
		alphabet = new char[R];
		inverse = new int[R];
		for (int i = 0; i < R; i++)
			alphabet[i] = (char) i;
		for (int i = 0; i < R; i++)
			inverse[i] = i;
	}
	public Alphabet(){
		this(256);
	}
	public boolean contains(char c){
		return inverse[c] != -1;
	}
	public int R(){
		return R;
	}
	public int radix(){
		return R;
	}
	public int lgR(){
		int lgR = 0;
		for (int t = R-1; t >= 1; t /= 2)
			lgR++;
		return lgR;
	}
	public int toIndex(char c){
		if (c >= inverse.length || inverse[c] == -1)
			throw new IllegalArgumentException("Character " + c + " not in alphabet");
		return inverse[c];
	}
	public int[] toIndices(String s){
		char[] source = s.toCharArray();
		int[] target = new int[s.length()];
		for (int i = 0; i < source.length; i++)
			target[i] = toIndex(source[i]);
		return target;
	}
	public char toChar(int index){
		if (index < 0 || index > R)
			throw new IndexOutOfBoundsException("Alphabet index out of bounds");
		return alphabet[index];
	}
	public String toChars(int[] indices){
		StringBuilder s = new StringBuilder(indices.length);
		for (int i = 0; i < indices.length; i++)
			s.append(toChar(indices[i]));
		return s.toString();
	}
	public static void main(String[] args){
		int[]  encoded1 = Alphabet.BASE64.toIndices("NowIsTheTimeForAllGoodMen");
		for (int i = 0; i < encoded1.length; i++)
			System.out.print(encoded1[i] + "  ");
		System.out.println();
		System.out.println(Alphabet.BASE64.toChar(5));
        String decoded1 = Alphabet.BASE64.toChars(encoded1);
        System.out.println(decoded1);
	}

}
