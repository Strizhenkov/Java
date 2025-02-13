public class Client {
    private String FirstName;
    private String SecondName;
    private int DocumentId;
    private String Address;
    public Client(String firstName, String secondName) {
        FirstName = firstName;
        SecondName = secondName;
    }
    public void AddDocument(int documentId) {
        DocumentId = documentId;
    }
    public void AddAddress(String address) {
        Address = address;
    }
    public long GetDocumentId() {
        return DocumentId;
    }
    public String GetAddress() {
        return Address;
    }
    public String GetName() {
        return FirstName + " " + SecondName;
    }
}
