$(function(){
	v = new Validator();
	v.rule({
		"user.email": {
			message: {
				require: "need email address",
				email: "wrong email format",
				ajax: isEmailUsed
			},
			label: "email-text"
		}
	},{
		valid_class: "text-success",
		invalid_calss: "text-error"
	});
});

function isEmailUsed(){
	
}

function createUser(){
	$("#user-register-form").submit();
}
