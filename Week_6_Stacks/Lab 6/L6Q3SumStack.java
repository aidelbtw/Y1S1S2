public class L6Q3SumStack {
    public int sumStack(L6Q1MyStack<Integer> S){
        int sum = 0;
        L6Q1MyStack<Integer> temp = new L6Q1MyStack();

        while (!S.isEmpty()){
            int value = S.pop();
            sum += value;
            temp.push(value);
        }

        while (!temp.isEmpty()) {
            S.push(temp.pop());
        }
        return sum;
    }
}
