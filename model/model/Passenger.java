package model;

public class Passenger{
    private int passengerId;
    private boolean survived;
    private Integer passengerClass;
    private String name;
    private String gender;
    private double age;
    private int sibSp;
    private int parch;
    private String ticket;
    private double fare;
    private String cabin;
    private String embarked;
    
    
    public Passenger(String passengerId, String survived, String passengerClass, String name, String gender, String age,
    String sibSp, String parch, String ticket, String fare, String cabin, String embarked) {
        this.passengerId = Integer.parseInt(passengerId);
        this.survived = survived == "0" ? false : true;
        this.passengerClass = Integer.parseInt(passengerClass);
        this.name = name;
        this.gender = gender;
        this.age = Double.parseDouble(age);
        this.sibSp = Integer.parseInt(sibSp);
        this.parch = Integer.parseInt(parch);
        this.ticket = ticket;
        this.fare = Double.parseDouble(fare);
        this.cabin = cabin;
        switch (embarked) {
            case "C":
                this.embarked = "Chebourg";
                break;
            case "S":
                this.embarked = "Southampton";
                break;    
            default:
                this.embarked = "Queenstown";
        };
        
    }

    public Passenger(String[] info) {
        this.passengerId = Integer.parseInt(info[0]);
        this.survived = info[1].equals("0") ? false : true;
        this.passengerClass = Integer.parseInt(info[2]);
        this.name = (info[3] + info[4]).substring(1, info[3].length() + info[4].length() - 1);
        this.gender = info[5];
        this.age = info[6].equals("") ? -1 : Double.parseDouble(info[6]);
        this.sibSp = Integer.parseInt(info[7]);
        this.parch = Integer.parseInt(info[8]);
        this.ticket = info[9];
        this.fare = Double.parseDouble(info[10]);
        this.cabin = info[11];
        if (info.length == 12) {
          this.embarked = "unknown";
        } else {
          switch (info[12]) {
            case "C":
              this.embarked = "Cherbourg";
              break;
            case "S":
              this.embarked = "Southampton";
              break;
            case "Q":
              this.embarked = "Queenstown";
              break;
            default:
              this.embarked = "unknown";
          }
        }
      }

    public int getPassengerId() {
        return passengerId;
    }

    public boolean isSurvived() {
        return survived;
    }

    public Integer getPassengerClass() {
        return passengerClass;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public double getAge() {
        return age;
    }

    public int getSibSp() {
        return sibSp;
    }

    public int getParch() {
        return parch;
    }

    public String getTicket() {
        return ticket;
    }

    public double getFare() {
        return fare;
    }

    public String getCabin() {
        return cabin;
    }

    public String getEmbarked() {
        return embarked;
    }

    @Override
    public String toString() {
        return "Passenger [age=" + age + ", cabin=" + cabin + ", embarked=" + embarked + ", fare=" + fare + ", gender="
                + gender + ", name=" + name + ", parch=" + parch + ", passengerClass=" + passengerClass
                + ", passengerId=" + passengerId + ", sibSp=" + sibSp + ", survived=" + survived + ", ticket=" + ticket
                + "]";
    }

    public String putToCsv() {
        return passengerId + "," + survived + "," + passengerClass + "," + name + "," + gender + "," + age + "," + sibSp
            + "," + parch + "," + ticket + "," + fare + "," + cabin + "," + embarked;
      }
    
}