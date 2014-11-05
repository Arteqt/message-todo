<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Message-Todo</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">


<!-- Custom styles for this template -->
<link href="http://getbootstrap.com/examples/jumbotron/jumbotron.css"
	rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<!-- <script src="../../assets/js/ie-emulation-modes-warning.js"></script> -->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">MESSAGE-TODO</a>
			</div>
			<div class="navbar-collapse collapse">
				<c:if test="${empty loggedUser}">
					<form action="home/login" method="post"
						class="navbar-form navbar-right" role="form">
						<div class="form-group">
							<input name="username" type="text" placeholder="Username"
								class="form-control">
						</div>
						<div class="form-group">
							<input name="password" type="password" placeholder="Password"
								class="form-control">
						</div>
						<button type="submit" class="btn btn-success">Sign in</button>
					</form>
				</c:if>
				<c:if test="${!empty loggedUser}">
					<pre>Welcome ${loggedUser.name}</pre>
					<form action="home/logout" method="get">
						<button type="submit" class="btn btn-success">Log-out</button>
					</form>
				</c:if>
			</div>
			<!--/.navbar-collapse -->
		</div>
	</div>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<div class="container">
			<h1>Your Messages</h1>
			<div class="tab-pane active" id="pills-stacked">
				<div class="tabbable">
					<ul class="nav nav-pills nav-stacked span2">
						<li class=""><a href="#tabs5-pane1" data-toggle="tab">Inbox<span
								class="badge">${inboxSize}</span></a></li>
						<li class="active"><a href="#tabs5-pane2" data-toggle="tab">Outbox<span
								class="badge">${outboxSize}</span></a></li>

					</ul>
					<div class="tab-content span5">
						<div id="tabs5-pane1" class="tab-pane">
							<table class="table table-condensed">
								<thead>
									<tr>
										<th width="80">Sender</th>
										<th width="80">Subject</th>
										<th width="160">Content</th>
										<th width="80">Date</th>
										<th width="60">Delete</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${inbox}" var="message">
										<tr class="clickableRow"
											data-url="messages/${message.id}">
											<td>${message.sender.name}</td>
											<td>${message.subject}</td>
											<td>${message.content}</td>
											<td>${message.date}</td>
											<td><a
												href="<c:url value='messages/delete/${user.id}' />">Delete</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div id="tabs5-pane2" class="tab-pane active">
							<table class="table table-condensed">
								<thead>
									<tr>
										<th width="80">Receiver</th>
										<th width="80">Subject</th>
										<th width="160">Content</th>
										<th width="80">Date</th>
										<th width="60">Delete</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${outbox}" var="message">
										<tr class="clickableRow"
											data-url="messages/${message.id}">
											<td>${message.receiver.name}</td>
											<td>${message.subject}</td>
											<td>${message.content}</td>
											<td>${message.date}</td>
											<td><a
												href="<c:url value='messages/delete/${message.id}' />">Delete</a></td>
										</tr>

										<br>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<!-- /.tab-content -->
				</div>
				<!-- /.tabbable -->
			</div>
		</div>
	</div>
	<div class="container">
		<!-- Example row of columns -->
		<div class="row">
			<h2>${messageDetailed.sender.name} ${messageDetailed.subject}</h2>
			<p>${messageDetailed.content}</p>

		</div>

		<hr>

		<footer>
			<p>&copy; Company 2014</p>
		</footer>
	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<!-- <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script> -->

	<script>
		jQuery(document).ready(function($) {
			$(".clickableRow").click(function() {
				window.document.location = $(this).data('url');
			});
		});
	</script>
</body>
</html>
