#set($page_title = "Hello")

<h2>Users</h2>
first: $first, limit: $limit
#foreach($user in $userList)
	$user.getId(): $user.getNome()
#end
<br />

<a href="$linkTo[$UserController].add()">Add</a>