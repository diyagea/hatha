<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link
	href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet">
	<script src="http://apps.bdimg.com/libs/jquery/2.0.0/jquery.min.js"></script>
	<script
		src="http://apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</head>
<body>


	<div class="panel panel-default">
		<div class="panel-body">
			<form class="form-inline text-center" role="form" action="uploadFile" enctype="multipart/form-data" method="post">
				
				<s:if test="errorMsg == null">
					<h2 class="alert alert-info">请上传要处理的文件，过程可能需要几分钟，请稍候片刻。  </h2>
				</s:if>
				<s:else>
					<h2 class="alert alert-danger"><s:property  value="errorMsg"/></h2>
				</s:else>
				<div class="form-group">
					<label class="sr-only" for="inputfile">文件输入</label> 
					<input type="file" name="upload" />
				</div>

				<button type="submit" class="btn btn-primary">提交</button>
			</form>
		</div>
	</div>

</body>
</html>
