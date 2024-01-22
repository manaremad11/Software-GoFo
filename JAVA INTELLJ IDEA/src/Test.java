public class Test {

        public static void main(String[] args)
        {
            String str = "1-hello";
            char ch = '2';
            int pos = 0;

            str = str.substring(0, pos) + ch + str.substring(pos + 1);
            System.out.println(str);
        }


}
