import java.io.IOException;
import java.net.URI;
import java.util.HashSet;

class SearchEngineHandler implements URLHandler {

    HashSet<String> db = new HashSet<>();

    @Override
    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("Search something!");
        } else if (url.getPath().equals("/add")) {
            String[] parameters = url.getQuery().split("=");
            db.add(parameters[1]);
            return parameters[1] + " added to list of strings.";
        } else if (url.getPath().equals("/search")) {
            String[] parameters = url.getQuery().split("=");
            String searchQuery = parameters[1];
            String result = "";
            for(String item: db) {
                if (item.contains(searchQuery)) {
                    result += item + "\n";
                }
            }
            return result;
        } else {
            return "404";
        }
        
    }

}

public class SearchEngine {
    //Copied from NumberServer.java
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new SearchEngineHandler());
    }
}
