package jogo.rmi;

class CustomException extends Exception
{
	private static final long serialVersionUID = 4195358804691165427L;

	
      public CustomException() {}

      
      public CustomException(String message)
      {
         super(message);
      }
 }