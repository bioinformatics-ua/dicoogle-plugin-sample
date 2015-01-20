/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.ieeta.dicoogle.plugin.demo.dicooglepluginsample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pt.ua.dicoogle.sdk.datastructs.SearchResult;
import pt.ua.dicoogle.sdk.task.Task;

/**
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 */
public class RSIJettyWebService  extends HttpServlet {
        
    
    private static RSIJettyPlugin plugin;


    public RSIJettyWebService() {
    }
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response)
                    throws ServletException, IOException {

        String SOPInstanceUID = req.getParameter("uid");
        if (SOPInstanceUID == null) {
                response.sendError(402, "No UID");
                return;
        }
        HashMap<String, String> extraFields = new HashMap<String, String>();
        //attaches the required extrafields

        extraFields.put("PatientName", "PatientName");
        extraFields.put("PatientID", "PatientID");
        extraFields.put("Modality", "Modality");
        extraFields.put("StudyDate", "StudyDate");
        extraFields.put("SeriesInstanceUID", "SeriesInstanceUID");
        extraFields.put("StudyID", "StudyID");
        extraFields.put("StudyInstanceUID", "StudyInstanceUID");
        extraFields.put("Thumbnail", "Thumbnail");
        extraFields.put("SOPInstanceUID", "SOPInstanceUID");

        Task<Iterable<SearchResult>> result = RSIPluginSet.coreDicoogle.query("lucene", "StudyInstanceUID:234567", extraFields);
        try {
            Iterable<SearchResult> rr = result.get();
            
                    
        } catch (InterruptedException ex) {
            Logger.getLogger(RSIJettyWebService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(RSIJettyWebService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out=response.getWriter();
        out.print("{\"action\":\"test\"}");
    }


    public static void setPlugin(RSIJettyPlugin plugin) {
            RSIJettyWebService.plugin = plugin;
    }

}
