import org.apache.commons.csv.*;
import edu.duke.*;
import java.io.*;
public class BabyBirths {
    public void printNames()
    {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser()){
            System.out.println("Name " + rec.get(0) +" Gender " + rec.get(1) + " Num Born " + rec.get(2));
        }
    }
    
    public void totalBirthday (FileResource fr){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int countGirlNamess =0;
        int countBoyNames =0;
        int countNames =0;
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")){
                totalBoys +=numBorn;
                countBoyNames++;
            }
            else{
                totalGirls +=numBorn;
                countGirlNamess++;
            }
            countNames++;
        }
        System.out.println("total Born = " + totalBirths +" with total Names =" + countNames);
        System.out.println("total Boys Born = " + totalBoys +" with total Names =" + countBoyNames);
        System.out.println("total Girls Born = " + totalGirls +" with total Names =" + countGirlNamess);
    }
    
    public int getRank(int year, String name, String gender)
    {
        int rank =0;
        FileResource fr = new FileResource("us_babynames_by_year/yob"+year+".csv");
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            if(rec.get(1).equals(gender))
            {
                rank++;
                if(rec.get(0).equals(name))
                {
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public String getName(int year, int rank, String gender)
    {
        int testRank =0;
        FileResource fr = new FileResource("us_babynames_by_year/yob"+year+".csv");
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            if(rec.get(1).equals(gender))
            {
                testRank++;
                if(testRank == rank)
                {
                    return rec.get(0);
                }
            }
        }
        
        return "NO NAME";
    }
    
    
    public String whatIsNameInYear(String name, int year, int newYear, String gender)
    {
        int rank = getRank(year, name, gender);
        if( rank == -1)
            return "NAME NOT EXIST IN THIS YEAR";
        String newName = getName(newYear, rank, gender);
        return newName;
    }

        public int yearOfHighestRank(String name, String gender)
    {
        DirectoryResource dr = new DirectoryResource();
        int smallestRank = 0;
        int returnedYear =-1;
        int highRank =0;
        for (File f : dr.selectedFiles())
        {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3,7));
            int currentRank = getRank(year,name,gender);
            if (highRank == 0 && currentRank != -1){
                highRank = currentRank;
                returnedYear = year;
            }
            else if ( currentRank < highRank && currentRank != -1)
            {
                highRank = currentRank;
                returnedYear = year;
            }
            
            
        }
        return returnedYear;
    }
    
    public double getAverageRank(String name, String gender)
    {
        DirectoryResource dr = new DirectoryResource();
        double returnedRank = -1;
        double sumRanks = 0;
        int yearsHaveName =0;
        for (File f : dr.selectedFiles())
        {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3,7));
            int currentRank = getRank(year,name,gender);
            yearsHaveName++;
            if (currentRank != -1)
            {
                sumRanks += currentRank;
            }
        }
        if (sumRanks == 0){
            return -1;
        }
        else
        {
            return sumRanks/yearsHaveName;
        }
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int nameRank = getRank(year,name,gender);
        FileResource fr = new FileResource("us_babynames_by_year/yob"+year+".csv");
        int totalBirthHigher =0;
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            
            ;
            if (rec.get(1).equals(gender) && nameRank > getRank(year,rec.get(0),gender)){
                int numBorn = Integer.parseInt(rec.get(2));
                totalBirthHigher +=numBorn;
            }
        }
        return totalBirthHigher;
    }
    
    // Fast communicate
    
        public String extractNameFromFile(FileResource fr,String gender,int rank)
        {
        int testRank =0;
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            if(rec.get(1).equals(gender))
            {
                testRank++;
                if(testRank == rank)
                {
                    return rec.get(0);
                }
            }
        }
        
        return "NO NAME";
    }
    
    public int extractIndexFromFile(FileResource fr,String gender,String name)    
    {
        int rank =0;
        for (CSVRecord rec : fr.getCSVParser(false))
        {
            if(rec.get(1).equals(gender))
            {
                rank++;
                if(rec.get(0).equals(name))
                {
                    return rank;
                }
            }
        }
        return -1;
    }
    

    // Tests
    
    public void testTotalBirthday()
    {
        FileResource fr = new FileResource();
        totalBirthday(fr);
    }
    
    public void testYearOfHighestRank()
    {
        System.out.println(yearOfHighestRank("Mason ","M"));
    }

}
