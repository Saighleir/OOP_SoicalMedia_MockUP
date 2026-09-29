
package oopa2_25.pkg26;

public class OOPA2_2526 {

   
    public static void main(String[] args) 
    {
        AppData.loadData();
        java.awt.EventQueue.invokeLater
        (
            new Runnable()
            {
                @Override
                public void run()
                {
                    AppData.scrSplash = new ScrSplash();

                    AppData.scrSplash.setVisible(true);
                }
            }
        );
        
    }//Main

     
}//Class
