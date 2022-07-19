import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonParser {

   private static final Pattern REGEX_ITEMS = Pattern.compile(".*\\[(.+)\\].*");
   
   public List<Movie> parse(String json){
   
      ObjectMapper mapper = new ObjectMapper();
      List<Movie> movies = null;

      Matcher matcher = REGEX_ITEMS.matcher(json);
      if (!matcher.find()) {

         throw new IllegalArgumentException("Nenhum filme encontrado.");
      }

      try{
         var m = mapper.readValue(json, Movie[].class);
         movies = Arrays.asList(m);
         System.out.println(m);
      }
      catch (JsonParseException e) { 
         e.getMessage();
         e.printStackTrace();
      }
      catch (JsonMappingException e) { 
         e.printStackTrace(); 
      }
      catch (IOException e) { 
         e.printStackTrace(); 
      }
      return movies;
   }
}