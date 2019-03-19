function personadd(){
     var medicalRecordNum=document.getElementById('patientno').value;
	 var name=document.getElementById('patientname').value;
	 var radiosex = document.getElementsByName("sex");
     	    for (i=0; i<radiosex.length; i++) {
     	        if (radiosex[i].checked) {
     	            var ssex=radiosex[i].value;
     	        }
     	    }
	 var age=document.getElementById('patientage').value;
	 var diagnosis=document.getElementById('patientdiagnosis').value;
	 var phone=document.getElementById('patientphone').value;
	 var list = document.getElementById("patientedu");
	 var index = list.selectedIndex; // 选中索引
     var eduLevel = list.options[index].value; // 选中值
     alert(name);
     alert(medicalRecordNum);
     alert(eduLevel);
     document.getElementById("patientname").value="somethind";
     if(patientname==undefined ||patientno==null  ||patientphone==null ){//此处添加非法字符以及空字符验证
    		 alert("重要信息未填写");
    	 }
    	 else{
    		 var formData=new FormData();
    		 formData.append('medicalRecordNum',medicalRecordNum);
    		 formData.append('name',name);
    		 formData.append('sex',ssex);
    		 formData.append('age',age);
    		 formData.append('diagnosis',diagnosis);
    		 formData.append('phone',patientphone);
    		 formData.append('eduLevel',eduLevel);
    		 $.ajax({
    			 url:"/admin/patientAdd",
    			 type:'post',
    			 datatype:'json',
    			 data:formData,
    			 processData: false,
    			 contentType: false,
    			 success: function(response) {
    				 if(response.state==0){
    	             	alert(response.message);
    	             }else{
    	             	alert(response.message);
    	             }
    			    }
    		 });
    	 }
}


		