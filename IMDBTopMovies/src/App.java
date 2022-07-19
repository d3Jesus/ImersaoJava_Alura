import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // fazer uma conexão HTTP e buscar os top 250 filmes
        String mostPopularMovies = "https://imdb-api.com/en/API/MostPopularMovies/k_e5ggpsqh";
        //String mostPopularTvs = "https://imdb-api.com/en/API/MostPopularTVs/k_e5ggpsqh";
        //String topTvs = "https://imdb-api.com/en/API/Top250TVs/k_e5ggpsqh";
        URI endereco = URI.create(mostPopularMovies);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados 
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("Título: " + filme.get("title"));
            System.out.println("Poster:" + filme.get("image"));
            System.out.println("Classificação" + filme.get("imDbRating"));
            System.out.println();
        }
    }
}
