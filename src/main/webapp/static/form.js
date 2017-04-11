/*
 * form submit validate
 */
var setting = {
	rules : {
		'a.name' : {
			required : true
		},
		'a.group' : {
			required : true
		}
	}
};
function myValidate($form, $setting){
	var errc = 0;
	$form.find('.form-group').removeClass('has-error');
	$.each($form.serialize().split('&'), function(i, e){
		var idx = e.indexOf('=');
		var k = e.substring(0, idx);
		var v = e.substring(idx+1);
		$.each($setting.rules, function(n, r){
			if(k == n){
				if(r.required == true){
					if(v == undefined || v == ''){
						$form.find('input[name="'+n+'"]').parent().parent().addClass('has-error');
						errc++;
					}
				}
			}
		});
	});
	return errc==0?true:false;
};