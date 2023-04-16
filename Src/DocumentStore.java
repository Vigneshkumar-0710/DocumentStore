//DocumentStore.java

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class DocumentStore {
    private Map<String, Document> documents;
   

    public DocumentStore() {
        this.documents = new HashMap<>();
    }

    public void addDocument(String path, Document document) {
        this.documents.put(path, document);
        System.out.println("File Added!");
    }

    public Document[] searchDocuments(String path) {
        return this.documents.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(path) || entry.getKey().startsWith(path + "/"))
                .map(Map.Entry::getValue)
                .toArray(Document[]::new);
    }

    public void deleteDocument(String path) {
        this.documents.remove(path);
        System.out.println("File Deleted!");
    }

    public static void main(String[] args) {
    	 boolean loop = true;
        DocumentStore store = new DocumentStore();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\t********************************<DOCUMENTSTORE>***********************************\n");
        
        System.out.print("\t**********************************************************************************\n");
        System.out.print("\t*               please type any option to proceed !                              *\n");
        System.out.print("\t*                                                                                *\n");
        System.out.print("\t*                  > add    - add a new document                                 *\n");
        System.out.print("\t*                  > search - search a document                                  *\n");
        System.out.print("\t*                  > delete - delete a document                                  *\n");
        
        System.out.print("\t*                  > quit                                                        *\n");
        System.out.print("\t*                                                                                *\n");
        
        System.out.print("\t**********************************************************************************\n");	
        System.out.println("\n\t**********************************************************************************\n");

        while (loop) {
            System.out.print("> ");

            String command = scanner.nextLine();
            String[] parts = command.split(" ");

            if (parts.length == 0) {
                continue;
            }
            

            switch (parts[0]) {
                case "add":
                    if (parts.length < 3) {
                        System.out.println("Need More Information (please specify input like add ../.../<path> <text>");
                        continue;
                    }
                    String addPath = parts[1];
                    String addText = String.join(" ", Arrays.copyOfRange(parts, 2, parts.length));
                    Document addDocument = new Document(addText);
                    store.addDocument(addPath, addDocument);
                    break;
                case "search":
                    if (parts.length != 2) {
                        System.out.println("Need More Information (please specify input like search ../.../<path> or ../<path> ");
                        continue;
                   }//else {
//                    	System.out.println("Not Found!");
//                    }
                    String searchPath = parts[1];
                    Document[] searchResults = store.searchDocuments(searchPath);
                    for (Document document : searchResults) {
                        System.out.println(document.text);
                    }
                    if(searchResults.length==0 ) {
                    	System.out.println("Not Found!");
                    }
                    break;
                case "delete":
                    if (parts.length != 2) {
                        System.out.println("Need More Information (please specify input like delete ../.../path");
                        continue;
                    }
                    String deletePath = parts[1];
                    store.deleteDocument(deletePath);
                    
                    break;
                case "quit":
                	loop=false;
                	System.out.println("Terminated!");
                	break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
    }
}
