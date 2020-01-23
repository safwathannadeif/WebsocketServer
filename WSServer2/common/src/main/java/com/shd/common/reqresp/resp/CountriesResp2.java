package com.shd.common.reqresp.resp;

import com.google.gson.Gson;

public class CountriesResp2{ 
  
  private String collectionName;
  private Country[] collection;
  private int maxNumCountries;
  private int numCountries;
  public CountriesResp2()
  {
	  
  }
  public class Country {
	  @Override
	public String toString() {
		return "Country [name=" + name + ", capital=" + capital + ", area=" + area + ", population=" + population + "]";
	}

	private String name ;
	  public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getPopulation() {
		return population;
	}

	public void setPopulation(double population) {
		this.population = population;
	}

	 private  String capital ;
	  private  double area ;
	  private double population ;
	  public Country ()
	  {
	  }
	  
	  public Country(String name, String capital, double area, double population) {
		  this.name=name ;
		  this.capital = capital ;
		  this.area = area ;
		  this.population= population ; 
  }
	  
	  }
  
  public Country newCountry(String name, String capital, double area, double population)
  {
	  return new Country(name,capital , area , population) ;
  }
  
  /**
   * Constructor that given the name of a country collection and the max number of countries in it
   * will create a Countries instance.
   * @param name String the name of the collection of countries.
   * @param n int the max number of countries in this collection
   * 
   * */
  public  CountriesResp2(String name, int n) {
    // creates an array of 5 Country objects, fills the array with
    // 5 new instances of the Country class, and then returns the array
    collectionName = name;
    maxNumCountries = n;
    collection = new Country[maxNumCountries];
    numCountries = 0;
  }
  
  /**
   * instance method that given a country instance will add it to this collection.
   * @param c Country the country to be added to the collection.
   * */
  public void addCountry(Country c) {
    collection[numCountries] = c;
    numCountries = numCountries+1;
  }
  
  
  /**
   * instance method that given the name, the capital, the area and the population of a country 
   * will add it to this collection.
   * @param name String the name of the country to be added to the collection.
   *  @param name String the name of the country to be added to the collection.
   *  @param capital String the capital of the country to be added to the collection.
   *  @param area double the area of the country to be added to the collection, in thousands Km^2.
   *  @param population double the population of the country to be added to the collection, in millions.
   * */
  public void addCountry(String name, String capital, double area, double population) {
    Country c = new Country(name, capital, area, population);
    this.addCountry(c);
  }
  
  /**
   * Instance method that returns the name of the country with capital the inout string.
   * @param capital String the given capital 
   * @return String the name of the country with the given capital
   * */
  public String searchCapital (String capital) {
    for (int i = 0; i < collection.length; i++)
      if (collection[i].getCapital().equalsIgnoreCase(capital)) {
      return collection[i].getName();
    }
    return "No country with that capital city";
  }
  
  /**
   * Instance method that returns the country with the maximum area in this collection
   * @return Country the country with the largest area in this collection
   * */
  public CountriesResp2 getListEuroZone()
  {
	  CountriesResp2 eurozone = new CountriesResp2("eurozone", 17);
	    
	    //country name, capital, area in thousands Km^2, population in millions
	    eurozone.addCountry("Austria", "Vienna", 83, 8.3);
	    eurozone.addCountry("Belgium", "Brussels", 30, 10);
	    eurozone.addCountry("Cyprus", "Nicosia", 9, 0.8);
	    eurozone.addCountry("Estonia", "Tallinn", 45, 1.3);
	    eurozone.addCountry("Finland", "Helsinki", 338, 5.3);
	    eurozone.addCountry("France", "Paris", 550, 64);
	    eurozone.addCountry("Germany", "Berlin", 356, 82);
	    Country greece = eurozone.newCountry( "Greece", "Athens", 131, 11);
	    eurozone.addCountry(greece);
	    eurozone.addCountry(eurozone.newCountry("Ireland", "Dublin", 70, 4.5));
	    eurozone.addCountry(eurozone.newCountry("Italy", "Rome", 301, 60));
	    eurozone.addCountry(eurozone.newCountry("Luxembourg", "Luxembourg", 2, 0.5));
	    eurozone.addCountry("Malta", "Malletta", 0.3, 0.4);
	    eurozone.addCountry("Netherlands", "Amsterdam", 41, 16);
	    eurozone.addCountry("Portugal", "Lisbon", 92, 10.6);
	    eurozone.addCountry("Slovakia", "Bratislava", 48, 5.4);
	    eurozone.addCountry("Slovenia", "Ljubljiana", 20, 2);
	    eurozone.addCountry("Spain", "Madrid", 504, 45);
	    return eurozone ;
  }
  public String CountriesResp2ToJsonStr() {
	Gson gson = new Gson();
	String jsonStr = gson.toJson(getListEuroZone());  
	return jsonStr ;
	
  }
  /**
   * Instance method that returns the country with the greatest population in this collection
   * @return Country the country with the greatest population in this collection
   * */
  public Country getMaxPopulationCountry () {
    // returns the name of the Country with the largest area
    int maxPopulationIndex = 0;  

    for (int i = 1; i < collection.length; i++)
      if (collection[maxPopulationIndex].getPopulation() < collection[i].getPopulation()) {
      maxPopulationIndex = i;
    }
    return collection[maxPopulationIndex];
  }
  
  /**
   * It checks whether the input country belongs to this collection, and returns true if so, 
   * false otherwise.
   * @param c Country to check whether it belongs to this collection.
   * @return boolean true if the given country is a member in this collection, false otherwise
   * */
  public boolean isMember(String other) {
    for (int i=0; i<numCountries; i++) {
      if (collection[i].getName().equalsIgnoreCase(other))
        return true;
    }
    return false;
  }
  
  /**
   * Instance method that returns a string representation of this collection of countries, one country per line.
   * @return  String  a string representation of this collection of countries
   */
  public String toString() {
    StringBuffer sb  = new StringBuffer() ;
    		
    		
    sb.append("\nThese are the countries in the " + collectionName + ": \n") ;
    for (int i=0; i<numCountries; i++)
    	{
    	sb.append(collection[i].toString() + "\n") ;
    	}
    
    return sb.toString();  
  }
  
  public static void main (String[] args) {
    //populate eurozone
//    System.out.println("...........................");
//    System.out.println("Creating eurozone with 17 countries");
//    
//    Countries eurozone = new Countries("eurozone", 17);
//    
//    //country name, capital, area in thousands Km^2, population in millions
//    eurozone.addCountry("Austria", "Vienna", 83, 8.3);
//    eurozone.addCountry("Belgium", "Brussels", 30, 10);
//    eurozone.addCountry("Cyprus", "Nicosia", 9, 0.8);
//    eurozone.addCountry("Estonia", "Tallinn", 45, 1.3);
//    eurozone.addCountry("Finland", "Helsinki", 338, 5.3);
//    eurozone.addCountry("France", "Paris", 550, 64);
//    eurozone.addCountry("Germany", "Berlin", 356, 82);
//    Country greece = eurozone.newCountry( "Greece", "Athens", 131, 11);
//    eurozone.addCountry(greece);
//    eurozone.addCountry(eurozone.newCountry("Ireland", "Dublin", 70, 4.5));
//    eurozone.addCountry(eurozone.newCountry("Italy", "Rome", 301, 60));
//    eurozone.addCountry(eurozone.newCountry("Luxembourg", "Luxembourg", 2, 0.5));
//    eurozone.addCountry("Malta", "Malletta", 0.3, 0.4);
//    eurozone.addCountry("Netherlands", "Amsterdam", 41, 16);
//    eurozone.addCountry("Portugal", "Lisbon", 92, 10.6);
//    eurozone.addCountry("Slovakia", "Bratislava", 48, 5.4);
//    eurozone.addCountry("Slovenia", "Ljubljiana", 20, 2);
//    
//    eurozone.addCountry("Spain", "Madrid", 504, 45);
	  CountriesResp2 countries = new CountriesResp2() ;
	  
	  CountriesResp2 eurozone =  countries.getListEuroZone() ;
	  
    System.out.println(eurozone);
    
    System.out.println("\nTesting searchCapital():");
    System.out.println("   Madrid is the capital of " + eurozone.searchCapital("Madrid"));
    System.out.println("   Athens is the capital of " + eurozone.searchCapital("Athens"));
    
    System.out.println("\nTesting getMaxAreaCountry():");
    //System.out.println("   Largest area in eurozone is " + eurozone.getMaxAreaCountry());
    
    System.out.println("\nTesting getMaxPopulationCountry():");
    System.out.println("   Largest population in eurozone is " + eurozone.getMaxPopulationCountry());
    
    System.out.println("\nTesting isMember():");
    System.out.println("   Is Spain a member of the eurozone? (true) - " + eurozone.isMember("spain"));
    System.out.println("   Is Greece a member of the eurozone? (true) - " + eurozone.isMember("greece"));
    System.out.println("   Is Korea a member of the eurozone? (false) - " + eurozone.isMember("korea"));
    System.out.println();
  }
  
}
