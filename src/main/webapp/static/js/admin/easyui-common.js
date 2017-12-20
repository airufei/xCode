//获取行号
function getRowIndex(target)
{
	  var rowNewIndex=0;
	   var  ttr=target;
	   for(var i=0;i<5;i++)
	   { 
		   if(ttr!=null)
		   {
		       ttr= ttr.parentNode;
		       if(ttr!=null)
		       {
				   if(ttr.tagName=="tr".toUpperCase()&&ttr.hasAttribute("datagrid-row-index"))
				   {
					  var index=ttr.getAttribute("datagrid-row-index");
					  rowNewIndex=parseInt(index);
				   }
		       }
		   }
	   }
	   return rowNewIndex;
}