package api;

import com.google.gson.JsonObject;
import lombok.Getter;

public class Project {
	@Getter
	private Long id;
	@Getter
	private String public_name;
	@Getter
	private String public_description;
	@Getter
	private String public_instructions;
	@Getter
	private String private_comment;
	@Getter
	private JsonObject task_spec;
	@Getter
	private AssignmentsIssuingType assignments_issuing_type;
	@Getter
	private Boolean assignments_automerge_enabled;
	@Getter
	private Integer max_active_assignments_count;
	@Getter
	private JsonObject quality_control;
	@Getter
	private JsonObject owner;
	@Getter
	private ProjectStatus status;
	@Getter
	private String created;
}