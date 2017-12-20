/*
 HTML5 Shiv v3.6.2pre | @afarkas @jdalton @jon_neal @rem | MIT/GPL2 Licensed
*/
(function(l,f){function m(){var a=e.elements;return"string"==typeof a?a.split(" "):a}function i(a){var b=n[a[o]];b||(b={},h++,a[o]=h,n[h]=b);return b}function p(a,b,c){b||(b=f);if(g)return b.createElement(a);c||(c=i(b));b=c.cache[a]?c.cache[a].cloneNode():r.test(a)?(c.cache[a]=c.createElem(a)).cloneNode():c.createElem(a);return b.canHaveChildren&&!s.test(a)?c.frag.appendChild(b):b}function t(a,b){if(!b.cache)b.cache={},b.createElem=a.createElement,b.createFrag=a.createDocumentFragment,b.frag=b.createFrag();
a.createElement=function(c){return!e.shivMethods?b.createElem(c):p(c,a,b)};a.createDocumentFragment=Function("h,f","return function(){var n=f.cloneNode(),c=n.createElement;h.shivMethods&&("+m().join().replace(/\w+/g,function(a){b.createElem(a);b.frag.createElement(a);return'c("'+a+'")'})+");return n}")(e,b.frag)}function q(a){a||(a=f);var b=i(a);if(e.shivCSS&&!j&&!b.hasCSS){var c,d=a;c=d.createElement("p");d=d.getElementsByTagName("head")[0]||d.documentElement;c.innerHTML="x<style>article,aside,figcaption,figure,footer,header,hgroup,nav,section{display:block}mark{background:#FF0;color:#000}</style>";
c=d.insertBefore(c.lastChild,d.firstChild);b.hasCSS=!!c}g||t(a,b);return a}var k=l.html5||{},s=/^<|^(?:button|map|select|textarea|object|iframe|option|optgroup)$/i,r=/^(?:a|b|code|div|fieldset|h1|h2|h3|h4|h5|h6|i|label|li|ol|p|q|span|strong|style|table|tbody|td|th|tr|ul)$/i,j,o="_html5shiv",h=0,n={},g;(function(){try{var a=f.createElement("a");a.innerHTML="<xyz></xyz>";j="hidden"in a;var b;if(!(b=1==a.childNodes.length)){f.createElement("a");var c=f.createDocumentFragment();b="undefined"==typeof c.cloneNode||
"undefined"==typeof c.createDocumentFragment||"undefined"==typeof c.createElement}g=b}catch(d){g=j=!0}})();var e={elements:k.elements||"abbr article aside audio bdi canvas data datalist details figcaption figure footer header hgroup mark meter nav output progress section summary time video",version:"3.6.2pre",shivCSS:!1!==k.shivCSS,supportsUnknownElements:g,shivMethods:!1!==k.shivMethods,type:"default",shivDocument:q,createElement:p,createDocumentFragment:function(a,b){a||(a=f);if(g)return a.createDocumentFragment();
for(var b=b||i(a),c=b.frag.cloneNode(),d=0,e=m(),h=e.length;d<h;d++)c.createElement(e[d]);return c}};l.html5=e;q(f)})(this,document);

/*!
 * selectivizr v1.0.2 - (c) Keith Clark, freely distributable under the terms of the MIT license.
 * selectivizr.com
 */
(function(j){function A(a){return a.replace(B,h).replace(C,function(a,d,b){for(var a=b.split(","),b=0,e=a.length;b<e;b++){var s=D(a[b].replace(E,h).replace(F,h))+o,l=[];a[b]=s.replace(G,function(a,b,c,d,e){if(b){if(l.length>0){var a=l,f,e=s.substring(0,e).replace(H,i);if(e==i||e.charAt(e.length-1)==o)e+="*";try{f=t(e)}catch(k){}if(f){e=0;for(c=f.length;e<c;e++){for(var d=f[e],h=d.className,j=0,m=a.length;j<m;j++){var g=a[j];if(!RegExp("(^|\\s)"+g.className+"(\\s|$)").test(d.className)&&g.b&&(g.b===!0||g.b(d)===!0))h=u(h,g.className,!0)}d.className=h}}l=[]}return b}else{if(b=c?I(c):!v||v.test(d)?{className:w(d),b:!0}:null)return l.push(b),"."+b.className;return a}})}return d+a.join(",")})}function I(a){var c=!0,d=w(a.slice(1)),b=a.substring(0,5)==":not(",e,f;b&&(a=a.slice(5,-1));var l=a.indexOf("(");l>-1&&(a=a.substring(0,l));if(a.charAt(0)==":")switch(a.slice(1)){case "root":c=function(a){return b?a!=p:a==p};break;case "target":if(m==8){c=function(a){function c(){var d=location.hash,e=d.slice(1);return b?d==i||a.id!=e:d!=i&&a.id==e}k(j,"hashchange",function(){g(a,d,c())});return c()};break}return!1;case "checked":c=function(a){J.test(a.type)&&k(a,"propertychange",function(){event.propertyName=="checked"&&g(a,d,a.checked!==b)});return a.checked!==b};break;case "disabled":b=!b;case "enabled":c=function(c){if(K.test(c.tagName))return k(c,"propertychange",function(){event.propertyName=="$disabled"&&g(c,d,c.a===b)}),q.push(c),c.a=c.disabled,c.disabled===b;return a==":enabled"?b:!b};break;case "focus":e="focus",f="blur";case "hover":e||(e="mouseenter",f="mouseleave");c=function(a){k(a,b?f:e,function(){g(a,d,!0)});k(a,b?e:f,function(){g(a,d,!1)});return b};break;default:if(!L.test(a))return!1}return{className:d,b:c}}function w(a){return M+"-"+(m==6&&N?O++:a.replace(P,function(a){return a.charCodeAt(0)}))}function D(a){return a.replace(x,h).replace(Q,o)}function g(a,c,d){var b=a.className,c=u(b,c,d);if(c!=b)a.className=c,a.parentNode.className+=i}function u(a,c,d){var b=RegExp("(^|\\s)"+c+"(\\s|$)"),e=b.test(a);return d?e?a:a+o+c:e?a.replace(b,h).replace(x,h):a}function k(a,c,d){a.attachEvent("on"+c,d)}function r(a,c){if(/^https?:\/\//i.test(a))return c.substring(0,c.indexOf("/",8))==a.substring(0,a.indexOf("/",8))?a:null;if(a.charAt(0)=="/")return c.substring(0,c.indexOf("/",8))+a;var d=c.split(/[?#]/)[0];a.charAt(0)!="?"&&d.charAt(d.length-1)!="/"&&(d=d.substring(0,d.lastIndexOf("/")+1));return d+a}function y(a){if(a)return n.open("GET",a,!1),n.send(),(n.status==200?n.responseText:i).replace(R,i).replace(S,function(c,d,b,e,f){return y(r(b||f,a))}).replace(T,function(c,d,b){d=d||i;return" url("+d+r(b,a)+d+") "});return i}function U(){var a,c;a=f.getElementsByTagName("BASE");for(var d=a.length>0?a[0].href:f.location.href,b=0;b<f.styleSheets.length;b++)if(c=f.styleSheets[b],c.href!=i&&(a=r(c.href,d)))c.cssText=A(y(a));q.length>0&&setInterval(function(){for(var a=0,c=q.length;a<c;a++){var b=q[a];if(b.disabled!==b.a)b.disabled?(b.disabled=!1,b.a=!0,b.disabled=!0):b.a=b.disabled}},250)}if(!/*@cc_on!@*/true){var f=document,p=f.documentElement,n=function(){if(j.XMLHttpRequest)return new XMLHttpRequest;try{return new ActiveXObject("Microsoft.XMLHTTP")}catch(a){return null}}(),m=/MSIE (\d+)/.exec(navigator.userAgent)[1];if(!(f.compatMode!="CSS1Compat"||m<6||m>8||!n)){var z={NW:"*.Dom.select",MooTools:"$$",DOMAssistant:"*.$",Prototype:"$$",YAHOO:"*.util.Selector.query",Sizzle:"*",jQuery:"*",dojo:"*.query"},t,q=[],O=0,N=!0,M="slvzr",R=/(\/\*[^*]*\*+([^\/][^*]*\*+)*\/)\s*/g,S=/@import\s*(?:(?:(?:url\(\s*(['"]?)(.*)\1)\s*\))|(?:(['"])(.*)\3))[^;]*;/g,T=/\burl\(\s*(["']?)(?!data:)([^"')]+)\1\s*\)/g,L=/^:(empty|(first|last|only|nth(-last)?)-(child|of-type))$/,B=/:(:first-(?:line|letter))/g,C=/(^|})\s*([^\{]*?[\[:][^{]+)/g,G=/([ +~>])|(:[a-z-]+(?:\(.*?\)+)?)|(\[.*?\])/g,H=/(:not\()?:(hover|enabled|disabled|focus|checked|target|active|visited|first-line|first-letter)\)?/g,P=/[^\w-]/g,K=/^(INPUT|SELECT|TEXTAREA|BUTTON)$/,J=/^(checkbox|radio)$/,v=m>6?/[\$\^*]=(['"])\1/:null,E=/([(\[+~])\s+/g,F=/\s+([)\]+~])/g,Q=/\s+/g,x=/^\s*((?:[\S\s]*\S)?)\s*$/,i="",o=" ",h="$1";(function(a,c){function d(){try{p.doScroll("left")}catch(a){setTimeout(d,50);return}b("poll")}function b(d){if(!(d.type=="readystatechange"&&f.readyState!="complete")&&((d.type=="load"?a:f).detachEvent("on"+d.type,b,!1),!e&&(e=!0)))c.call(a,d.type||d)}var e=!1,g=!0;if(f.readyState=="complete")c.call(a,i);else{if(f.createEventObject&&p.doScroll){try{g=!a.frameElement}catch(h){}g&&d()}k(f,"readystatechange",b);k(a,"load",b)}})(j,function(){for(var a in z){var c,d,b=j;if(j[a]){for(c=z[a].replace("*",a).split(".");(d=c.shift())&&(b=b[d]););if(typeof b=="function"){t=b;U();break}}}})}}})(this);

/*
css3-mediaqueries.js - CSS Helper and CSS3 Media Queries Enabler

author: Wouter van der Graaf <wouter at dynora nl>
version: 1.0 (20110330)
license: MIT
website: http://code.google.com/p/css3-mediaqueries-js/

W3C spec: http://www.w3.org/TR/css3-mediaqueries/

Note: use of embedded <style> is not recommended when using media queries, because IE  has no way of returning the raw literal css text from a <style> element.
*/


// true prototypal inheritance (http://javascript.crockford.com/prototypal.html)
if (typeof Object.create !== 'function') {
	Object.create = function (o) {
		function F() {}
		F.prototype = o;
		return new F();
	};
}


// user agent sniffing shortcuts
var ua = {
	toString: function () {
		return navigator.userAgent;
	},
	test: function (s) {
		return this.toString().toLowerCase().indexOf(s.toLowerCase()) > -1;
	}
};
ua.version = (ua.toString().toLowerCase().match(/[\s\S]+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [])[1];
ua.webkit = ua.test('webkit');
ua.gecko = ua.test('gecko') && !ua.webkit;
ua.opera = ua.test('opera');
ua.ie = ua.test('msie') && !ua.opera;
ua.ie6 = ua.ie && document.compatMode && typeof document.documentElement.style.maxHeight === 'undefined';
ua.ie7 = ua.ie && document.documentElement && typeof document.documentElement.style.maxHeight !== 'undefined' && typeof XDomainRequest === 'undefined';
ua.ie8 = ua.ie && typeof XDomainRequest !== 'undefined';



// initialize when DOM content is loaded
var domReady = function () {
	var fns = [];
	var init = function () {
		if (!arguments.callee.done) { // run init functions once
			arguments.callee.done = true;
			for (var i = 0; i < fns.length; i++) {
				fns[i]();
			}
		}
	};

	// listeners for different browsers
	if (document.addEventListener) {
		document.addEventListener('DOMContentLoaded', init, false);
	}
	if (ua.ie) {
		(function () {
			try {
				// throws errors until after ondocumentready
				document.documentElement.doScroll('left');

				// If we are in an iframe, the above does not work properly.
        // Trying to access the length attribute of document.body, however,
        // does throw an error until ondocumentready, fixing this issue.
        document.body.length;
			}
			catch (e) {
				setTimeout(arguments.callee, 50);
				return;
			}
			// no errors, fire
			init();
		})();
		// trying to always fire before onload
		document.onreadystatechange = function () {
			if (document.readyState === 'complete') {
				document.onreadystatechange = null;
				init();
			}
		};
	}
	if (ua.webkit && document.readyState) {
		(function () {
			if (document.readyState !== 'loading') {
				init();
			}
			else {
				setTimeout(arguments.callee, 10);
			}
		})();
	}
	window.onload = init; // fallback

  return function (fn) { // add fn to init functions
    if (typeof fn === 'function') {
      // If DOM ready has already been fired, fire the function
      // right away.
      if(init.done) {
        fn();
      } else {
        // Add to the queue
        fns[fns.length] = fn;
      }
    }
    return fn;
  };
}();


// helper library for parsing css to objects
var cssHelper = function () {

	var regExp = {
		BLOCKS: /[^\s{;][^{;]*\{(?:[^{}]*\{[^{}]*\}[^{}]*|[^{}]*)*\}/g,
		BLOCKS_INSIDE: /[^\s{][^{]*\{[^{}]*\}/g,
		DECLARATIONS: /[a-zA-Z\-]+[^;]*:[^;]+;/g,
		RELATIVE_URLS: /url\(['"]?([^\/\)'"][^:\)'"]+)['"]?\)/g,
		// strip whitespace and comments, @import is evil
		REDUNDANT_COMPONENTS: /(?:\/\*([^*\\\\]|\*(?!\/))+\*\/|@import[^;]+;|@-moz-document\s*url-prefix\(\)\s*{(([^{}])+{([^{}])+}([^{}])+)+})/g,
		REDUNDANT_WHITESPACE: /\s*(,|:|;|\{|\})\s*/g,
		WHITESPACE_IN_PARENTHESES: /\(\s*(\S*)\s*\)/g,
		MORE_WHITESPACE: /\s{2,}/g,
		FINAL_SEMICOLONS: /;\}/g,
		NOT_WHITESPACE: /\S+/g
	};

	var parsed, parsing = false;

	var waiting = [];
	var wait = function (fn) {
		if (typeof fn === 'function') {
			waiting[waiting.length] = fn;
		}
	};
	var ready = function () {
		for (var i = 0; i < waiting.length; i++) {
			waiting[i](parsed);
		}
	};
	var events = {};
	var broadcast = function (n, v) {
		if (events[n]) {
			var listeners = events[n].listeners;
			if (listeners) {
				for (var i = 0; i < listeners.length; i++) {
					listeners[i](v);
				}
			}
		}
	};

	var requestText = function (url, fnSuccess, fnFailure) {
		if (ua.ie && !window.XMLHttpRequest) {
			window.XMLHttpRequest = function () {
				return new ActiveXObject('Microsoft.XMLHTTP');
			};
		}
		if (!XMLHttpRequest) {
			return '';
		}
		var r = new XMLHttpRequest();
		try {
			r.open('get', url, true);
			r.setRequestHeader('X_REQUESTED_WITH', 'XMLHttpRequest');
		}
		catch (e) {
			fnFailure();
			return;
		}
		var done = false;
		setTimeout(function () {
			done = true;
		}, 5000);
		document.documentElement.style.cursor = 'progress';
		r.onreadystatechange = function () {
			if (r.readyState === 4 && !done) {
				if (!r.status && location.protocol === 'file:' ||
						(r.status >= 200 && r.status < 300) ||
						r.status === 304 ||
						navigator.userAgent.indexOf('Safari') > -1 && typeof r.status === 'undefined') {
					fnSuccess(r.responseText);
				}
				else {
					fnFailure();
				}
				document.documentElement.style.cursor = '';
				r = null; // avoid memory leaks
			}
		};
		r.send('');
	};

	var sanitize = function (text) {
		text = text.replace(regExp.REDUNDANT_COMPONENTS, '');
		text = text.replace(regExp.REDUNDANT_WHITESPACE, '$1');
        text = text.replace(regExp.WHITESPACE_IN_PARENTHESES, '($1)');
		text = text.replace(regExp.MORE_WHITESPACE, ' ');
		text = text.replace(regExp.FINAL_SEMICOLONS, '}'); // optional final semicolons
		return text;
	};

	var objects = {
	    stylesheet: function (el) {
	        var o = {};
	        var amqs = [], mqls = [], rs = [], rsw = [];
	        var s = el.cssHelperText;

	        // add attribute media queries
	        var attr = el.getAttribute('media');
	        if (attr) {
	            var qts = attr.toLowerCase().split(',')
	        }
	        else {
	            var qts = ['all'] // imply 'all'
            }
	        for (var i = 0; i < qts.length; i++) {
	            amqs[amqs.length] = objects.mediaQuery(qts[i], o);
	        }

	        // add media query lists and rules (top down order)
		    var blocks = s.match(regExp.BLOCKS); // @charset is not a block
		    if (blocks !== null) {
			    for (var i = 0; i < blocks.length; i++) {
				    if (blocks[i].substring(0, 7) === '@media ') { // media query (list)
					    var mql = objects.mediaQueryList(blocks[i], o);
					    rs = rs.concat(mql.getRules());
					    mqls[mqls.length] = mql;
				    }
				    else { // regular rule set, page context (@page) or font description (@font-face)
					    rs[rs.length] = rsw[rsw.length] = objects.rule(blocks[i], o, null);
				    }
			    }
		    }

	        o.element = el;
	        o.getCssText = function () {
	            return s;
	        };
	        o.getAttrMediaQueries = function () {
	            return amqs;
	        };
	        o.getMediaQueryLists = function () {
	            return mqls;
	        };
	        o.getRules = function () {
	            return rs;
	        };
	        o.getRulesWithoutMQ = function () {
	            return rsw;
	        };
	        return o;
	    },

		mediaQueryList: function (s, stsh) {
			var o = {};
			var idx = s.indexOf('{');
			var lt = s.substring(0, idx);
			s = s.substring(idx + 1, s.length - 1);
			var mqs = [], rs = [];

			// add media queries
			var qts = lt.toLowerCase().substring(7).split(',');
			for (var i = 0; i < qts.length; i++) { // parse each media query
				mqs[mqs.length] = objects.mediaQuery(qts[i], o);
			}

			// add rule sets
			var rts = s.match(regExp.BLOCKS_INSIDE);
			if (rts !== null) {
				for (i = 0; i < rts.length; i++) {
					rs[rs.length] = objects.rule(rts[i], stsh, o);
				}
			}

			o.type = 'mediaQueryList';
			o.getMediaQueries = function () {
				return mqs;
			};
			o.getRules = function () {
				return rs;
			};
			o.getListText = function () {
				return lt;
			};
			o.getCssText = function () {
				return s;
			};
			return o;
		},

		mediaQuery: function (s, listOrSheet) {
			s = s || '';
			var mql, stsh;
			if (listOrSheet.type === 'mediaQueryList') {
			    mql = listOrSheet;
		    }
		    else {
		        stsh = listOrSheet;
		    }
			var not = false, type;
			var expr = [];
			var valid = true;
			var tokens = s.match(regExp.NOT_WHITESPACE);



			for (var i = 0; i < tokens.length; i++) {
				var token = tokens[i];
				if (!type && (token === 'not' || token === 'only')) { // 'not' and 'only' keywords
					// keyword 'only' does nothing, as if it was not present
					if (token === 'not') {
						not = true;
					}
				}
				else if (!type) { // media type
					type = token;
				}
				else if (token.charAt(0) === '(') { // media feature expression
					var pair = token.substring(1, token.length - 1).split(':');
					expr[expr.length] = {
						mediaFeature: pair[0],
						value: pair[1] || null
					};
				}
			}

			return {
			    getQueryText: function () {
			        return s;
			    },
			    getAttrStyleSheet: function () {
			        return stsh || null;
			    },
				getList: function () {
					return mql || null;
				},
				getValid: function () {
					return valid;
				},
				getNot: function () {
					return not;
				},
				getMediaType: function () {
					return type;
				},
				getExpressions: function () {
					return expr;
				}
			};
		},

		rule: function (s, stsh, mql) {
			var o = {};
			var idx = s.indexOf('{');
			var st = s.substring(0, idx);
			var ss = st.split(',');
			var ds = [];
			var dts = s.substring(idx + 1, s.length - 1).split(';');
			for (var i = 0; i < dts.length; i++) {
				ds[ds.length] = objects.declaration(dts[i], o);
			}

			o.getStylesheet = function () {
			    return stsh || null;
			};
			o.getMediaQueryList = function () {
				return mql || null;
			};
			o.getSelectors = function () {
				return ss;
			};
			o.getSelectorText = function () {
				return st;
			};
			o.getDeclarations = function () {
				return ds;
			};
			o.getPropertyValue = function (n) {
				for (var i = 0; i < ds.length; i++) {
					if (ds[i].getProperty() === n) {
						return ds[i].getValue();
					}
				}
				return null;
			};
			return o;
		},

		declaration: function (s, r) {
			var idx = s.indexOf(':');
			var p = s.substring(0, idx);
			var v = s.substring(idx + 1);
			return {
				getRule: function () {
					return r || null;
				},
				getProperty: function () {
					return p;
				},
				getValue: function () {
					return v;
				}
			};
		}
	};

	var parseText = function (el) {
		if (typeof el.cssHelperText !== 'string') {
			return;
		}
		var o = {
		    stylesheet: null,
			mediaQueryLists: [],
			rules: [],
			selectors: {},
			declarations: [],
			properties: {}
		};

		// build stylesheet object
		var stsh = o.stylesheet = objects.stylesheet(el);

		// collect media query lists
		var mqls = o.mediaQueryLists = stsh.getMediaQueryLists();

		// collect all rules
		var ors = o.rules = stsh.getRules();

		// collect all selectors
		var oss = o.selectors;
		var collectSelectors = function (r) {
			var ss = r.getSelectors();
			for (var i = 0; i < ss.length; i++) {
				var n = ss[i];
				if (!oss[n]) {
					oss[n] = [];
				}
				oss[n][oss[n].length] = r;
			}
		};
		for (i = 0; i < ors.length; i++) {
			collectSelectors(ors[i]);
		}

		// collect all declarations
		var ods = o.declarations;
		for (i = 0; i < ors.length; i++) {
			ods = o.declarations = ods.concat(ors[i].getDeclarations());
		}

		// collect all properties
		var ops = o.properties;
		for (i = 0; i < ods.length; i++) {
			var n = ods[i].getProperty();
			if (!ops[n]) {
				ops[n] = [];
			}
			ops[n][ops[n].length] = ods[i];
		}

		el.cssHelperParsed = o;
		parsed[parsed.length] = el;
		return o;
	};

	var parseEmbedded = function (el, s) {
	    return;
	    // This function doesn't work because of a bug in IE, where innerHTML gives us parsed css instead of raw literal.
		el.cssHelperText = sanitize(s || el.innerHTML);
		return parseText(el);
	};

	var parse = function () {
		parsing = true;
		parsed = [];
		var linked = [];
		var finish = function () {
			for (var i = 0; i < linked.length; i++) {
				parseText(linked[i]);
			}
			var styles = document.getElementsByTagName('style');
			for (i = 0; i < styles.length; i++) {
				parseEmbedded(styles[i]);
			}
			parsing = false;
			ready();
		};
		var links = document.getElementsByTagName('link');
		for (var i = 0; i < links.length; i++) {
			var link = links[i];
			if (link.getAttribute('rel').indexOf('style') > -1 && link.href && link.href.length !== 0 && !link.disabled) {
				linked[linked.length] = link;
			}
		}
		if (linked.length > 0) {
			var c = 0;
			var checkForFinish = function () {
				c++;
				if (c === linked.length) { // parse in right order, so after last link is read
					finish();
				}
			};
			var processLink = function (link) {
				var href = link.href;
				requestText(href, function (text) {
					// fix url's
					text = sanitize(text).replace(regExp.RELATIVE_URLS, 'url(' + href.substring(0, href.lastIndexOf('/')) + '/$1)');
					link.cssHelperText = text;
					checkForFinish();
				}, checkForFinish);
			};
			for (i = 0; i < linked.length; i++) {
				processLink(linked[i]);
			}
		}
		else {
			finish();
		}
	};

	var types = {
	    stylesheets: 'array',
		mediaQueryLists: 'array',
		rules: 'array',
		selectors: 'object',
		declarations: 'array',
		properties: 'object'
	};

	var collections = {
	    stylesheets: null,
		mediaQueryLists: null,
		rules: null,
		selectors: null,
		declarations: null,
		properties: null
	};

	var addToCollection = function (name, v) {
		if (collections[name] !== null) {
			if (types[name] === 'array') {
				return (collections[name] = collections[name].concat(v));
			}
			else {
				var c = collections[name];
				for (var n in v) {
					if (v.hasOwnProperty(n)) {
						if (!c[n]) {
							c[n] = v[n];
						}
						else {
							c[n] = c[n].concat(v[n]);
						}
					}
				}
				return c;
			}
		}
	};

	var collect = function (name) {
		collections[name] = (types[name] === 'array') ? [] : {};
		for (var i = 0; i < parsed.length; i++) {
		    var pname = name === 'stylesheets' ? 'stylesheet' : name; // the exception
			addToCollection(name, parsed[i].cssHelperParsed[pname]);
		}
		return collections[name];
	};

	// viewport size
	var getViewportSize = function (d) {
		if (typeof window.innerWidth != 'undefined') {
			return window['inner' + d];
		}
		else if (typeof document.documentElement !== 'undefined'
				&& typeof document.documentElement.clientWidth !== 'undefined'
				&& document.documentElement.clientWidth != 0) {
			return document.documentElement['client' + d];
		}
	};

	// public static functions
	return {
		addStyle: function (s, mediaTypes, process) {
			var el;
			var styleElId = 'css-mediaqueries-js';
			var styleMedia = '';

			var styleEl = document.getElementById(styleElId);

			if (mediaTypes && mediaTypes.length > 0) {
			    styleMedia = mediaTypes.join(',');
					styleElId += styleMedia;
			}

      if (null !== styleEl) {
          el = styleEl;
      }
      else {
          el = document.createElement('style');
          el.setAttribute('type', 'text/css');
          el.setAttribute('id', styleElId);
          el.setAttribute('media', styleMedia);
          document.getElementsByTagName('head')[0].appendChild(el);
      }

      if (el.styleSheet) { // IE
          el.styleSheet.cssText += s;
      }
      else {
	        el.appendChild(document.createTextNode(s));
      }

			el.addedWithCssHelper = true;

			if (typeof process === 'undefined' || process === true) {
				cssHelper.parsed(function (parsed) {
					var o = parseEmbedded(el, s);
					for (var n in o) {
						if (o.hasOwnProperty(n)) {
							addToCollection(n, o[n]);
						}
					}
					broadcast('newStyleParsed', el);
				});
			}
			else {
				el.parsingDisallowed = true;
			}
			return el;
		},

		removeStyle: function (el) {
			if (el.parentNode)
				return el.parentNode.removeChild(el);
		},

		parsed: function (fn) {
			if (parsing) {
				wait(fn);
			}
			else {
				if (typeof parsed !== 'undefined') {
					if (typeof fn === 'function') {
						fn(parsed);
					}
				}
				else {
					wait(fn);
					parse();
				}
			}
		},

		stylesheets: function (fn) {
		    cssHelper.parsed(function (parsed) {
		        fn(collections.stylesheets || collect('stylesheets'));
		    });
		},

		mediaQueryLists: function (fn) {
			cssHelper.parsed(function (parsed) {
				fn(collections.mediaQueryLists || collect('mediaQueryLists'));
			});
		},

		rules: function (fn) {
			cssHelper.parsed(function (parsed) {
				fn(collections.rules || collect('rules'));
			});
		},

		selectors: function (fn) {
			cssHelper.parsed(function (parsed) {
				fn(collections.selectors || collect('selectors'));
			});
		},

		declarations: function (fn) {
			cssHelper.parsed(function (parsed) {
				fn(collections.declarations || collect('declarations'));
			});
		},

		properties: function (fn) {
			cssHelper.parsed(function (parsed) {
				fn(collections.properties || collect('properties'));
			});
		},

		broadcast: broadcast,

		addListener: function (n, fn) { // in case n is 'styleadd': added function is called everytime style is added and parsed
			if (typeof fn === 'function') {
				if (!events[n]) {
					events[n] = {
						listeners: []
					};
				}
				events[n].listeners[events[n].listeners.length] = fn;
			}
		},

		removeListener: function (n, fn) {
			if (typeof fn === 'function' && events[n]) {
				var ls = events[n].listeners;
				for (var i = 0; i < ls.length; i++) {
					if (ls[i] === fn) {
						ls.splice(i, 1);
						i -= 1;
					}
				}
			}
		},

		getViewportWidth: function () {
			return getViewportSize('Width');
		},

		getViewportHeight: function () {
			return getViewportSize('Height');
		}
	};
}();



// function to test and apply parsed media queries against browser capabilities
domReady(function enableCssMediaQueries() {
	var meter;

	var regExp = {
		LENGTH_UNIT: /[0-9]+(em|ex|px|in|cm|mm|pt|pc)$/,
		RESOLUTION_UNIT: /[0-9]+(dpi|dpcm)$/,
		ASPECT_RATIO: /^[0-9]+\/[0-9]+$/,
		ABSOLUTE_VALUE: /^[0-9]*(\.[0-9]+)*$/
	};

	var styles = [];

	var nativeSupport = function () {
		// check support for media queries
		var id = 'css3-mediaqueries-test';
		var el = document.createElement('div');
		el.id = id;
		var style = cssHelper.addStyle('@media all and (width) { #' + id +
			' { width: 1px !important; } }', [], false); // false means don't parse this temp style
		document.body.appendChild(el);
		var ret = el.offsetWidth === 1;
		style.parentNode.removeChild(style);
		el.parentNode.removeChild(el);
		nativeSupport = function () {
			return ret;
		};
		return ret;
	};

	var createMeter = function () { // create measuring element
		meter = document.createElement('div');
		meter.style.cssText = 'position:absolute;top:-9999em;left:-9999em;' +
			'margin:0;border:none;padding:0;width:1em;font-size:1em;'; // cssText is needed for IE, works for the others
		document.body.appendChild(meter);
		// meter must have browser default font size of 16px
		if (meter.offsetWidth !== 16) {
			meter.style.fontSize = 16 / meter.offsetWidth + 'em';
		}
		meter.style.width = '';
	};

	var measure = function (value) {
		meter.style.width = value;
		var amount = meter.offsetWidth;
		meter.style.width = '';
		return amount;
	};

	var testMediaFeature = function (feature, value) {
		// non-testable features: monochrome|min-monochrome|max-monochrome|scan|grid
		var l = feature.length;
		var min = (feature.substring(0, 4) === 'min-');
		var max = (!min && feature.substring(0, 4) === 'max-');

		if (value !== null) { // determine value type and parse to usable amount
			var valueType;
			var amount;
			if (regExp.LENGTH_UNIT.exec(value)) {
				valueType = 'length';
				amount = measure(value);
			}
			else if (regExp.RESOLUTION_UNIT.exec(value)) {
				valueType = 'resolution';
				amount = parseInt(value, 10);
				var unit = value.substring((amount + '').length);
			}
			else if (regExp.ASPECT_RATIO.exec(value)) {
				valueType = 'aspect-ratio';
				amount = value.split('/');
			}
			else if (regExp.ABSOLUTE_VALUE) {
				valueType = 'absolute';
				amount = value;
			}
			else {
				valueType = 'unknown';
			}
		}

		var width, height;
		if ('device-width' === feature.substring(l - 12, l)) { // screen width
			width = screen.width;
			if (value !== null) {
				if (valueType === 'length') {
					return ((min && width >= amount) || (max && width < amount) || (!min && !max && width === amount));
				}
				else {
					return false;
				}
			}
			else { // test width without value
				return width > 0;
			}
		}
		else if ('device-height' === feature.substring(l - 13, l)) { // screen height
			height = screen.height;
			if (value !== null) {
				if (valueType === 'length') {
					return ((min && height >= amount) || (max && height < amount) || (!min && !max && height === amount));
				}
				else {
					return false;
				}
			}
			else { // test height without value
				return height > 0;
			}
		}
		else if ('width' === feature.substring(l - 5, l)) { // viewport width
			width = document.documentElement.clientWidth || document.body.clientWidth; // the latter for IE quirks mode
			if (value !== null) {
				if (valueType === 'length') {
					return ((min && width >= amount) || (max && width < amount) || (!min && !max && width === amount));
				}
				else {
					return false;
				}
			}
			else { // test width without value
				return width > 0;
			}
		}
		else if ('height' === feature.substring(l - 6, l)) { // viewport height
			height = document.documentElement.clientHeight || document.body.clientHeight; // the latter for IE quirks mode
			if (value !== null) {
				if (valueType === 'length') {
					return ((min && height >= amount) || (max && height < amount) || (!min && !max && height === amount));
				}
				else {
					return false;
				}
			}
			else { // test height without value
				return height > 0;
			}
		}
		else if ('orientation' === feature.substring(l - 11, l)) { // orientation

			width = document.documentElement.clientWidth || document.body.clientWidth; // the latter for IE quirks mode
			height = document.documentElement.clientHeight || document.body.clientHeight; // the latter for IE quirks mode

			if (valueType === 'absolute') {
				return (amount === 'portrait') ? (width <= height) : (width > height);
			}
			else {
				return false;
			}
		}
		else if ('aspect-ratio' === feature.substring(l - 12, l)) { // window aspect ratio
			width = document.documentElement.clientWidth || document.body.clientWidth; // the latter for IE quirks mode
			height = document.documentElement.clientHeight || document.body.clientHeight; // the latter for IE quirks mode

			var curRatio = width / height;
			var ratio = amount[1] / amount[0];

			if (valueType === 'aspect-ratio') {
				return ((min && curRatio >= ratio) || (max && curRatio < ratio) || (!min && !max && curRatio === ratio));
			}
			else {
				return false;
			}
		}
		else if ('device-aspect-ratio' === feature.substring(l - 19, l)) { // screen aspect ratio
			return valueType === 'aspect-ratio' && screen.width * amount[1] === screen.height * amount[0];
		}
		else if ('color-index' === feature.substring(l - 11, l)) { // number of colors
			var colors = Math.pow(2, screen.colorDepth);
			if (value !== null) {
				if (valueType === 'absolute') {
					return ((min && colors >= amount) || (max && colors < amount) || (!min && !max && colors === amount));
				}
				else {
					return false;
				}
			}
			else { // test height without value
				return colors > 0;
			}
		}
		else if ('color' === feature.substring(l - 5, l)) { // bits per color component
			var color = screen.colorDepth;
			if (value !== null) {
				if (valueType === 'absolute') {
					return ((min && color >= amount) || (max && color < amount) || (!min && !max && color === amount));
				}
				else {
					return false;
				}
			}
			else { // test height without value
				return color > 0;
			}
		}
		else if ('resolution' === feature.substring(l - 10, l)) {
			var res;
			if (unit === 'dpcm') {
				res = measure('1cm');
			}
			else {
				res = measure('1in');
			}
			if (value !== null) {
				if (valueType === 'resolution') {
					return ((min && res >= amount) || (max && res < amount) || (!min && !max && res === amount));
				}
				else {
					return false;
				}
			}
			else { // test height without value
				return res > 0;
			}
		}
		else {
			return false;
		}
	};

	var testMediaQuery = function (mq) {
		var test = mq.getValid();
		var expressions = mq.getExpressions();
		var l = expressions.length;
		if (l > 0) {
			for (var i = 0; i < l && test; i++) {
				test = testMediaFeature(expressions[i].mediaFeature, expressions[i].value);
			}
			var not = mq.getNot();
			return (test && !not || not && !test);
		}
		return test;
	};

	var testMediaQueryList = function (mql, ts) {
	    // ts is null or an array with any media type but 'all'.
		var mqs = mql.getMediaQueries();
		var t = {};
		for (var i = 0; i < mqs.length; i++) {
		    var type = mqs[i].getMediaType();
		    if (mqs[i].getExpressions().length === 0) {
		        continue;
		        // TODO: Browser check! Assuming old browsers do apply the bare media types, even in a list with media queries.
		    }
		    var typeAllowed = true;
		    if (type !== 'all' && ts && ts.length > 0) {
		        typeAllowed = false;
		        for (var j = 0; j < ts.length; j++) {
		            if (ts[j] === type) {
		                typeAllowed = true;
                    }
		        }
		    }
			if (typeAllowed && testMediaQuery(mqs[i])) {
				t[type] = true;
			}
		}
		var s = [], c = 0;
		for (var n in t) {
			if (t.hasOwnProperty(n)) {
				if (c > 0) {
					s[c++] = ',';
				}
				s[c++] = n;
			}
		}
		if (s.length > 0) {
			styles[styles.length] = cssHelper.addStyle('@media ' + s.join('') + '{' + mql.getCssText() + '}', ts, false);
		}
	};

	var testMediaQueryLists = function (mqls, ts) {
		for (var i = 0; i < mqls.length; i++) {
			testMediaQueryList(mqls[i], ts);
		}
	};

	var testStylesheet = function (stsh) {
	    var amqs = stsh.getAttrMediaQueries();
	    var allPassed = false;
	    var t = {};
		for (var i = 0; i < amqs.length; i++) {
			if (testMediaQuery(amqs[i])) {
				t[amqs[i].getMediaType()] = amqs[i].getExpressions().length > 0;
			}
		}
		var ts = [], tswe = [];
		for (var n in t) {
			if (t.hasOwnProperty(n)) {
				ts[ts.length] = n;
				if (t[n]) {
				    tswe[tswe.length] = n
				}
			    if (n === 'all') {
			        allPassed = true;
                }
			}
		}
		if (tswe.length > 0) { // types with query expressions that passed the test
		    styles[styles.length] = cssHelper.addStyle(stsh.getCssText(), tswe, false);
		}
		var mqls = stsh.getMediaQueryLists();
		if (allPassed) {
		    // If 'all' in media attribute passed the test, then test all @media types in linked CSS and create style with those types.
		    testMediaQueryLists(mqls);
		}
		else {
		    // Or else, test only media attribute types that passed the test and also 'all'.
		    // For positive '@media all', create style with attribute types that passed their test.
		    testMediaQueryLists(mqls, ts);
	    }
    };

	var testStylesheets = function (stshs) {
	    for (var i = 0; i < stshs.length; i++) {
	        testStylesheet(stshs[i]);
	    }
	    if (ua.ie) {
			// force repaint in IE
			document.documentElement.style.display = 'block';
			setTimeout(function () {
				document.documentElement.style.display = '';
			}, 0);
			// delay broadcast somewhat for IE
			setTimeout(function () {
				cssHelper.broadcast('cssMediaQueriesTested');
			}, 100);
		}
		else {
			cssHelper.broadcast('cssMediaQueriesTested');
		}
	};

	var test = function () {
		for (var i = 0; i < styles.length; i++) {
			cssHelper.removeStyle(styles[i]);
		}
		styles = [];
		cssHelper.stylesheets(testStylesheets);
	};

	var scrollbarWidth = 0;
	var checkForResize = function () {
		var cvpw = cssHelper.getViewportWidth();
		var cvph = cssHelper.getViewportHeight();

		// determine scrollbar width in IE, see resizeHandler
		if (ua.ie) {
			var el = document.createElement('div');
			el.style.position = 'absolute';
			el.style.top = '-9999em';
			el.style.overflow = 'scroll';
			document.body.appendChild(el);
			scrollbarWidth = el.offsetWidth - el.clientWidth;
			document.body.removeChild(el);
		}

		var timer;
		var resizeHandler = function () {
			var vpw = cssHelper.getViewportWidth();
			var vph = cssHelper.getViewportHeight();
			// check whether vp size has really changed, because IE also triggers resize event when body size changes
			// 20px allowance to accomodate short appearance of scrollbars in IE in some cases
			if (Math.abs(vpw - cvpw) > scrollbarWidth || Math.abs(vph - cvph) > scrollbarWidth) {
				cvpw = vpw;
				cvph = vph;
				clearTimeout(timer);
				timer = setTimeout(function () {
					if (!nativeSupport()) {
						test();
					}
					else {
						cssHelper.broadcast('cssMediaQueriesTested');
					}
				}, 500);
			}
		};

		window.onresize = function () {
			var x = window.onresize || function () {}; // save original
			return function () {
				x();
				resizeHandler();
			};
		}();
	};

	// prevent jumping of layout by hiding everything before painting <body>
    var docEl = document.documentElement;
	docEl.style.marginLeft = '-32767px';

	// make sure it comes back after a while
	setTimeout(function () {
		docEl.style.marginLeft = '';
	}, 5000);

	return function () {
		if (!nativeSupport()) { // if browser doesn't support media queries
			cssHelper.addListener('newStyleParsed', function (el) {
				testStylesheet(el.cssHelperParsed.stylesheet);
			});
			// return visibility after media queries are tested
			cssHelper.addListener('cssMediaQueriesTested', function () {
				// force repaint in IE by changing width
				if (ua.ie) {
					docEl.style.width = '1px';
				}
				setTimeout(function () {
					docEl.style.width = ''; // undo width
					docEl.style.marginLeft = ''; // undo hide
				}, 0);
				// remove this listener to prevent following execution
				cssHelper.removeListener('cssMediaQueriesTested', arguments.callee);
			});
			createMeter();
			test();
		}
		else {
			docEl.style.marginLeft = ''; // undo visibility hidden
		}
		checkForResize();
	};
}());


// bonus: hotfix for IE6 SP1 (bug KB823727)
try {
	document.execCommand('BackgroundImageCache', false, true);
} catch (e) {}
