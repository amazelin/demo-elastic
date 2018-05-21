package com.mazelin.demo.elastic.batch;


/**
 * Created by dauvin on 12/04/2017.
 */
public class DBResourceResolver {

	public static final String DB_RESOURCES_CLASS_NAME = "com.clam.mediaplus.services.external.Resources";
	public static final String DB_RESOURCE_NAME_SUFIX = "_DS";

	private final String schema;

	private final String entity;

	private final boolean ignoreEntity;

	public DBResourceResolver(String schema) {
		this(schema, false);
	}

	public DBResourceResolver(String schema, boolean ignoreEntity) {

		this.schema = schema;
		this.ignoreEntity = ignoreEntity;
		this.entity = System.getenv("ENTITY") != null ? System.getenv("ENTITY") : System.getProperty("ENTITY");
	}

	public String getDBResourceStaticFieldName() {
		return DB_RESOURCES_CLASS_NAME + "." + this.schema + DB_RESOURCE_NAME_SUFIX;
	}

}
