//package com.metube.common.func;
//
//import java.io.IOException;
//import java.util.AbstractMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.constraints.NotNull;
//
//import org.springframework.web.servlet.ModelAndView;
//
//import lombok.Builder;
//
//public class ResultModel {
//   
//   private HttpServletResponse response;
//   private ModelAndView mv;
//   
//   @Builder
//   protected ResultModel (
//         @NotNull HttpServletResponse response,
//         Map<String, Object> map,
//         AbstractMap.SimpleEntry<String, Object> object,
//         String view,
//         Integer status,
//         String resultMessage,
//         String redirection
//   ) throws IOException {
//	   this.response = response;
//       this.mv = new ModelAndView();
//        
//       if(status == null) {
//    	   this.response.setStatus(200);
//           this.mv.addObject("resultMessage", "success");
//       }
//       else {
//           this.response.setStatus(status);
//           this.mv.addObject("resultMessage", resultMessage==null?"":resultMessage);
//       }
//
//       if(map != null) 
//    	   this.mv.addAllObjects(map);
//	   if(object != null) 
//		   this.mv.addObject(object.getKey(), object.getValue());
//	
//	   this.mv.setViewName(view);
//	    
//	   if(redirection != null) 
//		   response.sendRedirect(redirection);
//   }
//   
//   public static ModelAndView PageNotFound(@NotNull HttpServletResponse response) throws IOException {
//      return ResultModel.builder()
//            .response(response)
//            .status(404)
//            .resultMessage("Not Found")
//            // @TODO : insert into here 404 page
////            .view("channel")
//            .build()
//            .getModelAndView();
//   }
//   
//   public static ModelAndView ServerError(@NotNull HttpServletResponse response) throws IOException {
//      return ResultModel.builder()
//            .response(response)
//            .status(500)
//            .resultMessage("Internal Server Error")
//            // @TODO : insert into here 500 page
////            .view("channel")
//            .build()
//            .getModelAndView();
//   }
//   
//   public void setStatus(int status) {
//        this.response.setStatus(status);
//    }
//
//    public void setViewName(String viewName) {
//        this.mv.setViewName(viewName);
//    }
//
//    public void setData(String id, Object data) {
//        this.mv.addObject(id, data);
//    }
//
//    public Object getData(String key) {
//        return this.mv.getModel().get(key);
//    }
//    
//   public ModelAndView getModelAndView() {
//        return this.mv;
//    }
//}