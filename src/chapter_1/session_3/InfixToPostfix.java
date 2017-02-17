package chapter_1.session_3;
/**
 * ϰ��1.3.10��������ʽת��Ϊ������ʽ
 * @author ZhangYuliang
 *
 */
public class InfixToPostfix {
	/**
	 * ����˼·̫���ң����ܲ��Ͻ�����Ҫ�Ľ�
	 * @param infix
	 * @return postfix
	 */
	public static String turn(String infix){
		String result = "";
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < infix.length();i++){
			Character item = infix.charAt(i);
			if (Character.isDigit(item)){
				result += item;
				continue;
			}
			else if (stack.size() == 0){
				stack.push(item);
				continue;
			}
			if (item == '+' || item == '-'){
				while (stack.peak() != null && (stack.peak() == '*' || stack.peak() == '/'))
					result += stack.pop();
				if (stack.size() == 0 || stack.peak() == '(' || stack.peak() == '+' || stack.peak() == '-')
					stack.push(item);
			}
			if (item == '*' || item == '/'){
				stack.push(item);
			}
			if (item == ')'){
				while (stack.peak() != '(')
					result += stack.pop();
				stack.pop();
			}
			if (item == '('){
				stack.push(item);
			}
		}
		while (stack.size() > 0)
			result += stack.pop();
		return result;
	}
	public static boolean isNumber(Character num){
		if (Character.isDigit(num))
			return true;
		else return false;
	}
	public static double calculatePostfix(String postfix){
		Stack<Double> numStack = new Stack<Double>();
		for (int i = 0; i < postfix.length(); i++){
			Character item = postfix.charAt(i);
			if (Character.isDigit(item))
				numStack.push(Double.parseDouble(item.toString()));
			else{
				Double num2 = numStack.pop();
				Double num1 = numStack.pop();
				Double result = doOperate(item, num1, num2);
				numStack.push(result);
			}
		}
		return numStack.pop();
	}
	public static double doOperate(Character c, Double num1, Double num2){
		switch (c){
		case '+':
			return num1 + num2;
		case '-':
			return num1 - num2;
		case '*':
			return num1 * num2;
		case '/':
			if (num2 == 0)
				return 0;
			return num1 / num2;
		}
		return 0;
	}
	public static void test(){
		String infix = "6/(4-2)+(3*(1+1))";
//		String infix = "1+1*1";
//		String infix = "1+1*1+1/2";
//		String infix = "(1+1)*1+1/2";
		//Ӧ��Ϊ��642-/311+*+
		//������Ӧ��Ϊ9
		String postfix = turn(infix);
		System.out.println(postfix);
		System.out.println(calculatePostfix(postfix));
	}
	public static void main(String[] args){
		InfixToPostfix.test();
	}
}
