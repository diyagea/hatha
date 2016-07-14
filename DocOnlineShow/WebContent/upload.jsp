<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<form class="form-inline text-center" role="form" action="process-docs.jsp" enctype="multipart/form-data" method="post">
				<h2>请上传要处理的文件，过程可能需要几分钟，请稍候片刻。  </h2>
				<div class="form-group">
					<label class="sr-only" for="inputfile">文件输入</label> 
					<input type="file" id="inputfile" name="file1">
				</div>

				<button type="submit" class="btn btn-primary">提交</button>
			</form>
		</div>
	</div>

</body>
</html>
