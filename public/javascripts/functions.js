/**
 * 表单验证
 */
function Field(id){
	this.id = id;
	this.valid = false;
	this.validations = []
}

function Validator(){
	this.fields = [];
}

Validator.prototype.rule = function(fields, classes){
	this.valid_class = classes['valid_class'];
	this.invalid_class = classes['invalid_class'];
	
	for (var key in fields) {
		field = new Field(key);
		field.label = fields[key]['label'];
		for (var key in fields) {
			
		}
		this.fields.push(field);
	}
}