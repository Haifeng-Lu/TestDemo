package com.datamaster.util;


public class Response {
    private int state;
    private String message;
    private Object data;

   
	
	
	
	
    public Response(Object data){
		this.message = "查询成功";
		this.data = data;
	}
	
	
	public Response(String message){
		this.state = 0;
		this.message = message;
	}
	
	public Response(){
		
	}

    public Response(int state) {
        this.state = state;
    }

    public Response(int state, String message) {
        this.state = state;
        this.message = message;
    }

    public Response(int state, String message, Object data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    
    @Override
	public String toString() {
		return "Response [state=" + state + ", message=" + message + ", data=" + data + "]";
	}
}

