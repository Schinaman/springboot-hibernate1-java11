package com.example.course.services.exceptions;

public class DatabaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DatabaseException(String msg) {
		super(msg); //"could not execute statement; SQL [n/a]; constraint [\"FKPWLYWCCHG27NUBKWTFJP2P7YT: PUBLIC.TB_ORDER FOREIGN KEY(CLIENTE_ID) REFERENCES PUBLIC.TB_USER(ID) (1)\"; SQL statement:\ndelete from tb_user where id=? [23503-200]]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"

	}
	
	

}
