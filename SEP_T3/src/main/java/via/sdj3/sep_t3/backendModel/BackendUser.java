package via.sdj3.sep_t3.backendModel;

public class BackendUser
{
        private int id;
        private String username;
        private String password;
        private String fullName;
        private Double rating;
        private String phoneNo;
        private String address;

        public BackendUser(int id, String username, String password, String fullName, String phoneNo, String address) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.fullName = fullName;
            this.rating = 3.0;
            this.phoneNo = phoneNo;
            this.address = address;
        }

        public int getId() {
            return id;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getFullName() {
            return fullName;
        }
        public Double getRating() {
            return rating;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public String getAddress() {
            return address;
        }
}
