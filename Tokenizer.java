public class Tokenizer {
    public static int getToken(String dato){
        int token=0;
        switch (dato) {
            case "org":
                token = 1125;
                break;
            case "package":
                token = 1130;
                break;
            case ".":
                token=3030;
                break;
            case "example":
                token = 1055;
                break;
            default:
                break;
        }
        return token;
    }
}
