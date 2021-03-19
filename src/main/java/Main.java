import api.Project;
import api.endpoint.Endpoints;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		JsonElement element = Connection.getDataBy(Endpoints.GET_PROJECTS);
		print(element);

		JsonObject jsonObject = element.getAsJsonObject();
		JsonArray jsonArray = jsonObject.getAsJsonArray("items");

		Set<Project> projectSet = new HashSet<Project>();

		Gson gson = new Gson();
		for (int i = 0; i < jsonArray.size(); i++) {
			Project project = gson.fromJson(jsonArray.get(i), Project.class);
			projectSet.add(project);
		}

		for (Project p : projectSet) {
			System.out.println(p.getId());
			System.out.println(p.getPublic_name());
			System.out.println(p.getPublic_description());
			System.out.println(p.getPublic_instructions());
			print(p.getTask_spec().get("view_spec"));
			System.out.println(p.getAssignments_issuing_type());
			System.out.println(p.getAssignments_automerge_enabled());
			print(p.getOwner());
			System.out.println(p.getStatus());
			System.out.println(p.getCreated());
		}
	}

	private static void print(JsonElement element) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(element));
	}
}