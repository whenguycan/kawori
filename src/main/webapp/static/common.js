/*
 * form submit validate
 */
var defaultSetting = {
	rules : {
		'name' : {
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
				if(r.number == true){
					var reg = /^[0-9]*$/;
					if(!v.match(reg)){
						$form.find('input[name="'+n+'"]').parent().parent().addClass('has-error');
						errc++;
					}
				}
			}
		});
	});
	$.each($form.find('select'), function(i, e){
		var k = $(e).attr('name');
		var v = $(e).val();
		$.each($setting.rules, function(n, r){
			if(k == n){
				if(r.required == true){
					if(v == undefined || v == ''){
						$form.find('select[name="'+n+'"]').parent().parent().addClass('has-error');
						errc++;
					}
				}
			}
		});
	});
	return errc==0?true:false;
};
function formSubmit(button, setting, callback){
	var $form = $(button).parent();
	if(myValidate($form, setting) == true){
		var url = $form.attr('action');
		var data = $form.serialize();
		$.ajax({
			url : url,
			type : 'post',
			data : data,
			dataType : 'json',
			success : function(data){
				if(callback){
					callback(JSON.parse(data));
				}
			}
		});
	}
};