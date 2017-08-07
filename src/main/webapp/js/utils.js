/**
 * Utils.js
 * 
 * Wayne Wan(wanyi@yihaodian.com) 5:40:58 PM Sep 27, 2011 2011
 */
GlobalUtils = {
	showLoadMask : function() {
		$("#loadMask").show();
		$("#loadMask-tips").show();
	},
	hideLoadMask : function() {
		$("#loadMask").hide();
		$("#loadMask-tips").hide();
	}
}
/**
 * MD5 utility
 * sample:
 * MD5Utils.MD5("aaa");
 */
MD5Utils = {
	hex_chr : "0123456789abcdef",
	rhex : function(num) {
		var str = "";
		for (j = 0; j <= 3; j++)
			str += this.hex_chr.charAt((num >> (j * 8 + 4)) & 0x0F)
					+ this.hex_chr.charAt((num >> (j * 8)) & 0x0F);
		return str;
	},
	str2blks_MD5 : function(str) {
		var nblk = ((str.length + 8) >> 6) + 1;
		var blks = new Array(nblk * 16);
		for (i = 0; i < nblk * 16; i++)
			blks[i] = 0;
		for (i = 0; i < str.length; i++)
			blks[i >> 2] |= str.charCodeAt(i) << ((i % 4) * 8);
		blks[i >> 2] |= 0x80 << ((i % 4) * 8);
		blks[nblk * 16 - 2] = str.length * 8;
		return blks;
	},
	add : function(x, y) {
		var lsw = (x & 0xFFFF) + (y & 0xFFFF);
		var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
		return (msw << 16) | (lsw & 0xFFFF);
	},
	rol : function(num, cnt) {
		return (num << cnt) | (num >>> (32 - cnt));
	},
	cmn : function(q, a, b, x, s, t) {
		return this.add(this.rol(this.add(this.add(a, q), this.add(x, t)), s), b);
	},
	ff : function(a, b, c, d, x, s, t) {
		return this.cmn((b & c) | ((~b) & d), a, b, x, s, t);
	},
	gg : function(a, b, c, d, x, s, t) {
		return this.cmn((b & d) | (c & (~d)), a, b, x, s, t);
	},
	hh : function(a, b, c, d, x, s, t) {
		return this.cmn(b ^ c ^ d, a, b, x, s, t);
	},
	ii : function(a, b, c, d, x, s, t) {
		return this.cmn(c ^ (b | (~d)), a, b, x, s, t);
	},
	MD5 : function(str) {
		x = this.str2blks_MD5(str);
		var a = 1732584193;
		var b = -271733879;
		var c = -1732584194;
		var d = 271733878;
		for (i = 0; i < x.length; i += 16) {
			var olda = a;
			var oldb = b;
			var oldc = c;
			var oldd = d;
			a = this.ff(a, b, c, d, x[i + 0], 7, -680876936);
			d = this.ff(d, a, b, c, x[i + 1], 12, -389564586);
			c = this.ff(c, d, a, b, x[i + 2], 17, 606105819);
			b = this.ff(b, c, d, a, x[i + 3], 22, -1044525330);
			a = this.ff(a, b, c, d, x[i + 4], 7, -176418897);
			d = this.ff(d, a, b, c, x[i + 5], 12, 1200080426);
			c = this.ff(c, d, a, b, x[i + 6], 17, -1473231341);
			b = this.ff(b, c, d, a, x[i + 7], 22, -45705983);
			a = this.ff(a, b, c, d, x[i + 8], 7, 1770035416);
			d = this.ff(d, a, b, c, x[i + 9], 12, -1958414417);
			c = this.ff(c, d, a, b, x[i + 10], 17, -42063);
			b = this.ff(b, c, d, a, x[i + 11], 22, -1990404162);
			a = this.ff(a, b, c, d, x[i + 12], 7, 1804603682);
			d = this.ff(d, a, b, c, x[i + 13], 12, -40341101);
			c = this.ff(c, d, a, b, x[i + 14], 17, -1502002290);
			b = this.ff(b, c, d, a, x[i + 15], 22, 1236535329);
			a = this.gg(a, b, c, d, x[i + 1], 5, -165796510);
			d = this.gg(d, a, b, c, x[i + 6], 9, -1069501632);
			c = this.gg(c, d, a, b, x[i + 11], 14, 643717713);
			b = this.gg(b, c, d, a, x[i + 0], 20, -373897302);
			a = this.gg(a, b, c, d, x[i + 5], 5, -701558691);
			d = this.gg(d, a, b, c, x[i + 10], 9, 38016083);
			c = this.gg(c, d, a, b, x[i + 15], 14, -660478335);
			b = this.gg(b, c, d, a, x[i + 4], 20, -405537848);
			a = this.gg(a, b, c, d, x[i + 9], 5, 568446438);
			d = this.gg(d, a, b, c, x[i + 14], 9, -1019803690);
			c = this.gg(c, d, a, b, x[i + 3], 14, -187363961);
			b = this.gg(b, c, d, a, x[i + 8], 20, 1163531501);
			a = this.gg(a, b, c, d, x[i + 13], 5, -1444681467);
			d = this.gg(d, a, b, c, x[i + 2], 9, -51403784);
			c = this.gg(c, d, a, b, x[i + 7], 14, 1735328473);
			b = this.gg(b, c, d, a, x[i + 12], 20, -1926607734);
			a = this.hh(a, b, c, d, x[i + 5], 4, -378558);
			d = this.hh(d, a, b, c, x[i + 8], 11, -2022574463);
			c = this.hh(c, d, a, b, x[i + 11], 16, 1839030562);
			b = this.hh(b, c, d, a, x[i + 14], 23, -35309556);
			a = this.hh(a, b, c, d, x[i + 1], 4, -1530992060);
			d = this.hh(d, a, b, c, x[i + 4], 11, 1272893353);
			c = this.hh(c, d, a, b, x[i + 7], 16, -155497632);
			b = this.hh(b, c, d, a, x[i + 10], 23, -1094730640);
			a = this.hh(a, b, c, d, x[i + 13], 4, 681279174);
			d = this.hh(d, a, b, c, x[i + 0], 11, -358537222);
			c = this.hh(c, d, a, b, x[i + 3], 16, -722521979);
			b = this.hh(b, c, d, a, x[i + 6], 23, 76029189);
			a = this.hh(a, b, c, d, x[i + 9], 4, -640364487);
			d = this.hh(d, a, b, c, x[i + 12], 11, -421815835);
			c = this.hh(c, d, a, b, x[i + 15], 16, 530742520);
			b = this.hh(b, c, d, a, x[i + 2], 23, -995338651);
			a = this.ii(a, b, c, d, x[i + 0], 6, -198630844);
			d = this.ii(d, a, b, c, x[i + 7], 10, 1126891415);
			c = this.ii(c, d, a, b, x[i + 14], 15, -1416354905);
			b = this.ii(b, c, d, a, x[i + 5], 21, -57434055);
			a = this.ii(a, b, c, d, x[i + 12], 6, 1700485571);
			d = this.ii(d, a, b, c, x[i + 3], 10, -1894986606);
			c = this.ii(c, d, a, b, x[i + 10], 15, -1051523);
			b = this.ii(b, c, d, a, x[i + 1], 21, -2054922799);
			a = this.ii(a, b, c, d, x[i + 8], 6, 1873313359);
			d = this.ii(d, a, b, c, x[i + 15], 10, -30611744);
			c = this.ii(c, d, a, b, x[i + 6], 15, -1560198380);
			b = this.ii(b, c, d, a, x[i + 13], 21, 1309151649);
			a = this.ii(a, b, c, d, x[i + 4], 6, -145523070);
			d = this.ii(d, a, b, c, x[i + 11], 10, -1120210379);
			c = this.ii(c, d, a, b, x[i + 2], 15, 718787259);
			b = this.ii(b, c, d, a, x[i + 9], 21, -343485551);
			a = this.add(a, olda);
			b = this.add(b, oldb);
			c = this.add(c, oldc);
			d = this.add(d, oldd);
		}
		return this.rhex(a) + this.rhex(b) + this.rhex(c) + this.rhex(d);
	}
}

CheckBoxUtils = {
	checkAll : function(eleName){
		var chks = document.getElementsByName(eleName);
		CheckBoxUtils.checkAllByEles(chks);
	},
	checkAllByEles : function(chks){
		if(chks){
			for(var i=0; i<chks.length;i++){
				if(!chks[i].disabled)
					chks[i].checked = true;
			}
		}
	},
	uncheckAll : function(eleName){
		var chks = document.getElementsByName(eleName);
		CheckBoxUtils.uncheckAllByEles(chks);
	},
	uncheckAllByEles: function(chks){
		if(chks){
			for(var i=0; i<chks.length;i++){
				if(!chks[i].disabled)
					chks[i].checked = false;
			}
		}
	},
	inverseCheckByEles : function(chks){
		if(chks){
			for(var i=0; i<chks.length;i++){
				if(!chks[i].disabled)
					chks[i].checked = !chks[i].checked;
			}
		}
	},
	inverseCheck : function(eleName){
		var chks = document.getElementsByName(eleName);
		CheckBoxUtils.inverseCheckByEles(chks);
	},
	/**
	 * only used by report module
	 * @param eleIds
	 */
	checkSpevify : function(eleIds){
		for(var i=0; i<eleIds.length;i++){
			var chk = document.getElementById("report_" + eleIds[i]);
			if(chk){
				if(!chk.disabled)
					chk.checked = true;
			}
		}
	},
	checkValidate : function(eleName, minCount, maxCount){
		var chks = document.getElementsByName(eleName);
		var checkCount = 0;
		if(chks){
			for(var i=0; i<chks.length;i++){
				if(chks[i].checked) checkCount++;
			}
			if(checkCount<minCount){
				alert("至少选择" + minCount + "个！");
				return false;
			}
			if(maxCount&&checkCount>maxCount){
				alert("至多选择" + maxCount + "个！");
				return false;
			}
		}
	}
}

/**
 * format number to "112,333,333.00"
 * @param s
 * @returns
 */
function formatCurrency(s){
	if (isNaN(parseFloat(s))) { return "0.00"}
    s = (s+"").replace(/^(\d*)$/,"$1.");
    s=(s+"00").replace(/(\d*\.\d\d)\d*/,"$1");
    s=s.replace(".",",");
    var re=/(\d)(\d{3},)/;
    while(re.test(s))
           s=s.replace(re,"$1,$2");
    s=s.replace(/,(\d\d)$/,".$1");
    return s.replace(/^\./,"0.")
}

/**
 * format number to "112,333,333"
 * @param s
 * @returns
 */
function formatNumber(s){
	if (isNaN(parseFloat(s))) { return "0" }
	s = s+"";
    var re=/(\d+)(\d{3})/;
    while(re.test(s)){
           s=s.replace(re,"$1,$2");
	}
    s=s.replace(/,(\d\d)$/,".$1");
    return s.replace(/^\./,"0.")
}

/**
 * 会对array进行重新排序
 * @returns {Array}
 */
Array.prototype.unique = function(){
	this.sort(function(a, b){
		if(a>b) return 1;
		if(a<b) return -1;
		return 0;
	})
	var r = [];
	for(var i=0;i<this.length-1;i++){
		if(this[i]!=this[i+1]){
			r.push(this[i]);
			if(i==(this.length-2)){
				r.push(this[i+1]);
			}
		}else if(i==(this.length-2)){
			r.push(this[i]);
		}
	}
	return r;
}

/**
 * 不对array进行重新排序
 * @returns {Array}
 */
Array.prototype.unique2 = function(){
	var r = [];
	for(var i=0;i<this.length;i++){
		if($.inArray(this[i],r)==-1) r.push(this[i]);
	}
	return r;
}