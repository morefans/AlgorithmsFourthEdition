package chapter_1.session_3;

/**
 * 习题1.3.4，给包含花括号、方括号、圆括号的字符串进行左右匹配是否正确搭配闭合
 * @author ZhangYuliang
 *
 */
public class Parentheses {
	public static final char LEFT_PARENTHESIS = '('; 
	public static final char RIGHT_PARENTHESIS = ')'; 
	public static final char LEFT_BRACE = '{'; 
	public static final char RIGHT_BRACE = '}'; 
	public static final char LEFT_BRACKET = '['; 
	public static final char RIGHT_BRACKET = ']';
	
	public static boolean judgeBalance(String input){
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < input.length(); i++){
			if (input.charAt(i) == RIGHT_PARENTHESIS){
				Character item = stack.pop();
				if (item == null || item != LEFT_PARENTHESIS)
					return false;
			}
			else if (input.charAt(i) == RIGHT_BRACE){
				Character item = stack.pop();
				if (item == null || item != LEFT_BRACE)
					return false;
			}
			else if (input.charAt(i) == RIGHT_BRACKET){
				Character item = stack.pop();
				if (item == null || item != LEFT_BRACKET)
					return false;
			}
			else{
				stack.push(input.charAt(i));
			}
		}
		return stack.isEmpty();
	}
	public static void main(String[] args){
		System.out.println(Parentheses.judgeBalance("[()"));
		System.out.println(Parentheses.judgeBalance("[(])"));
		System.out.println(Parentheses.judgeBalance("[()]{}{[()()]}"));
	}
	
	public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == LEFT_PARENTHESIS)
            	stack.push(LEFT_PARENTHESIS);
            if (s.charAt(i) == LEFT_BRACE)
            	stack.push(LEFT_BRACE);
            if (s.charAt(i) == LEFT_BRACKET)
            	stack.push(LEFT_BRACKET);
            if (s.charAt(i) == RIGHT_PARENTHESIS) {
                if (stack.isEmpty())
                	return false;
                if (stack.pop() != LEFT_PARENTHESIS)
                	return false;
            }
            else if (s.charAt(i) == RIGHT_BRACE) {
                if (stack.isEmpty())
                	return false;
                if (stack.pop() != LEFT_BRACE)
                	return false;
            }
            else if (s.charAt(i) == RIGHT_BRACKET) {
                if (stack.isEmpty())
                	return false;
                if (stack.pop() != LEFT_BRACKET)
                	return false;
            }
        }
        return stack.isEmpty();
    }

}
