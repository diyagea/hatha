<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
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


	<h2 class="text-center">������Ʒ�б�</h2>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Ʒ��</th>
				<th>������</th>
				<th>�������</th>
				<th>��������</th>
				<th>��Ʒ�����ܼ�</th>
				<th>��Ʒ�ɱ��ܽ�</th>
				<th>��������</th>
				<th>�ɽ����</th>
				<th>�����ܽ��</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator var="h" value="hathas">
				<tr>
					<td>${h.name}</td>
					<td>${h.code}</td>
					<td>${h.date}</td>
					<td>${h.shop}</td>
					<td>${h.totalPrice}</td>
					<td>${h.cost}</td>
					<td>${h.amount}</td>
					<td>${h.turnover}</td>
					<td>${h.quotedPrice}</td>

				</tr>
			</s:iterator>
		</tbody>

	</table>
	<ul class="pagination">
		<li><a href="findByPage?curPage=${curPage-1}">&laquo;</a></li>
		<li><a href="findByPage?curPage=${curPage+1}">&raquo;</a></li>
	</ul>


</body>
</html>
