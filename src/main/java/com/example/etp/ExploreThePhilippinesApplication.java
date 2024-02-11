package com.example.etp;

import com.example.etp.domain.Popularity;
import com.example.etp.domain.Region;
import com.example.etp.service.BeachService;
import com.example.etp.service.IslandService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@SpringBootApplication
public class ExploreThePhilippinesApplication implements CommandLineRunner {

	@Value("${etp.importfile}")
	private String importFile;

	@Autowired
	private IslandService islandService;

	@Autowired
	private BeachService beachService;

	public static void main(String[] args) {
		ConfigurableApplicationContext apc = SpringApplication.run(ExploreThePhilippinesApplication.class, args);
		//The for loop gives me a list of all the beans in springs ioc container. Any class that's annotated with @Component
		//becomes a bean. A bean is an instance of a class managed by springs ioc container
		for(String st : apc.getBeanDefinitionNames()){
			System.out.println(st);
		}
	}

	@Override
	public void run(String... args) throws Exception {
		createAllIslands();
		createBeaches(importFile);
	}

	private void createAllIslands() {
		islandService.createIsland("PLN", "Palawan");
		islandService.createIsland("SQJR", "Siquijor");
		islandService.createIsland("BHL", "Bohol");
		islandService.createIsland("BRCY", "Boracay");
	}

	private void createBeaches(String fileToImport) throws IOException {
		BeachFromFile.read(fileToImport).forEach(importedBeach ->
				beachService.createBeach(importedBeach.getTitle(),
						importedBeach.getDescription(),
						importedBeach.getLength(),
						importedBeach.getSand(),
						importedBeach.getKeywords(),
						importedBeach.getIslandName(),
						importedBeach.getPopularity(),
						importedBeach.getRegion()));
	}

	private static class BeachFromFile {
		//Beach fields
		private String islandName, title, description, length, sand, keywords, popularity, region;

		//Beach file reader
		static List<BeachFromFile> read(String fileToImport) throws IOException {
			return new ObjectMapper().setVisibility(FIELD, ANY).
					readValue(new FileInputStream(fileToImport), new TypeReference<List<BeachFromFile>>() {
					});
		}

		protected BeachFromFile() {
		}

		public String getTitle() {
			return title;
		}

		public String getDescription() {
			return description;
		}

		public String getLength() {
			return length;
		}

		public String getSand() {
			return sand;
		}

		public String getKeywords() {
			return keywords;
		}

		public String getIslandName() {
			return islandName;
		}

		public Popularity getPopularity() {
			return Popularity.valueOf(popularity);
		}

		public Region getRegion() {
			return Region.findByLabel(region);
		}

	}

}
