

/**
 * Module to show Recently Viewed Products
 *
 * Copyright (c) 2014 Caroline Schnapp (11heavens.com)
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 *
 */

Haravan.Products = (function() {
	var a = {
		howManyToShow: 3,
		howManyToStoreInMemory: 10,
		wrapperId: "recently-viewed-products",
		templateId: "recently-viewed-product-template",
		onComplete: null
	};
	var c = [];
	var h = null;
	var d = null;
	var e = 0;
	var b = {
		configuration: {
			expires: 90,
			path: "/",
			domain: window.location.hostname
		},
		name: "haravan_recently_viewed",
		write: function(i) {
			jQuery.cookie(this.name, i.join(" "), this.configuration)
		},
		read: function() {
			var i = [];
			var j = jQuery.cookie(this.name);
			if (j !== null) {
				i = j.split(" ")
			}
			return i
		},
		destroy: function() {
			jQuery.cookie(this.name, null, this.configuration)
		},
		remove: function(k) {
			var j = this.read();
			var i = jQuery.inArray(k, j);
			if (i !== -1) {
				j.splice(i, 1);
				this.write(j)
			}
		}
	};
	var f = function() {
		h.show();
		if (a.onComplete) {
			try {
				a.onComplete()
			} catch (i) {}
		}
	};
	var g = function() {
		if (c.length && e < a.howManyToShow) {
			jQuery.ajax({
				dataType: "json",
				url: "/products/" + c[0] + ".js",
				cache: false,
				success: function(i) {
					d.tmpl(i).appendTo(h);
					c.shift();
					e++;
					g()
				},
				error: function() {
					b.remove(c[0]);
					c.shift();
					g()
				}
			})
		} else {
			f()
		}
	};
	return {
		resizeImage: function(m, j) {
			if (j == null) {
				return m
			}
			if (j == "master") {
				return m.replace(/http(s)?:/, "")
			}
			var i = m.match(/\.(jpg|jpeg|gif|png|bmp|bitmap|tiff|tif)(\?v=\d+)?/i);
			if (i != null) {
				var k = m.split(i[0]);
				var l = i[0];
				return (k[0] + "_" + j + l).replace(/http(s)?:/, "")
			} else {
				return null
			}
		},
		showRecentlyViewed: function(i) {
			var i = i || {};
			jQuery.extend(a, i);
			c = b.read();
			d = jQuery("#" + a.templateId);
			h = jQuery("#" + a.wrapperId);
			a.howManyToShow = Math.min(c.length, a.howManyToShow);
			if (a.howManyToShow && d.length && h.length) {
				g()
			}
		},
		getConfig: function() {
			return a
		},
		clearList: function() {
			b.destroy()
		},
		recordRecentlyViewed: function(l) {
			var l = l || {};
			jQuery.extend(a, l);
			var j = b.read();
			if (window.location.pathname.indexOf("/products/") !== -1) {
				var k = window.location.pathname.match(/\/products\/([a-z0-9\-]+)/)[1];
				var i = jQuery.inArray(k, j);
				if (i === -1) {
					j.unshift(k);
					j = j.splice(0, a.howManyToStoreInMemory)
				} else {
					j.splice(i, 1);
					j.unshift(k)
				}
				b.write(j)
			}
		}
	}
})(); 