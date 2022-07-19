import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class App {
    public static void main(String[] args) throws Exception {
        // Font Size
        String FONT_BOLD = "\u001b[1m";
        String FONT_RESET = "\033[0m";
        String FONT_ITALIC = "\u001B[3m";

        // Background colors
        String COLOR_TITLE_GREEN = "\u001b[42m";
        String COLOR_IMAGE_BRIGHT_CYAN = "\u001b[46;1m";
        String COLOR_CLASSIFICATION_MAGENTA = "\u001b[45m";
        String COLOR_RESET = "\u001b[47;1m";

        String key = System.getenv("IMDB_KEY");

        // fazer uma conexão HTTP e buscar os top 250 filmes
        String mostPopularMovies = "https://imdb-api.com/en/API/MostPopularMovies/" + key;
        //String mostPopularTvs = "https://imdb-api.com/en/API/MostPopularTVs/k_e5ggpsqh";
        //String topTvs = "https://imdb-api.com/en/API/Top250TVs/k_e5ggpsqh";
        URI endereco = URI.create(mostPopularMovies);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JacksonParser();
        var listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados 
        for (var filme : listaDeFilmes) {
            System.out.println(FONT_ITALIC + COLOR_TITLE_GREEN + "Título: " + FONT_BOLD + filme.getTitle() + COLOR_RESET + FONT_RESET);
            System.out.println(COLOR_IMAGE_BRIGHT_CYAN + "Poster: " + FONT_BOLD + filme.getImage() + COLOR_RESET + FONT_RESET);
            System.out.println(COLOR_CLASSIFICATION_MAGENTA + "Classificaçao :" + FONT_BOLD + filme.getImDbRating() + COLOR_RESET + FONT_RESET);
            System.out.println();
        } 
    }
}
