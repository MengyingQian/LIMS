(function() {
    var n, f, i, r, u, t = function(n, t) {
        return function() {
            return n.apply(t, arguments)
        }
    }, e = [].indexOf || function(n) {
        for (var t = 0, i = this.length; i > t; t++)
            if (t in this && this[t] === n)
                return t;
        return -1
    }
    ;
    f = function() {
        function n() {}
        return n.prototype.extend = function(n, t) {
            var i, r;
            for (i in t)
                r = t[i],
                null == n[i] && (n[i] = r);
            return n
        }
        ,
        n.prototype.isMobile = function(n) {
            return /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(n)
        }
        ,
        n.prototype.createEvent = function(n, t, i, r) {
            var u;
            return null == t && (t = !1),
            null == i && (i = !1),
            null == r && (r = null),
            null != document.createEvent ? (u = document.createEvent("CustomEvent"),
            u.initCustomEvent(n, t, i, r)) : null != document.createEventObject ? (u = document.createEventObject(),
            u.eventType = n) : u.eventName = n,
            u
        }
        ,
        n.prototype.emitEvent = function(n, t) {
            return null != n.dispatchEvent ? n.dispatchEvent(t) : t in (null != n) ? n[t]() : "on" + t in (null != n) ? n["on" + t]() : void 0
        }
        ,
        n.prototype.addEvent = function(n, t, i) {
            return null != n.addEventListener ? n.addEventListener(t, i, !1) : null != n.attachEvent ? n.attachEvent("on" + t, i) : n[t] = i
        }
        ,
        n.prototype.removeEvent = function(n, t, i) {
            return null != n.removeEventListener ? n.removeEventListener(t, i, !1) : null != n.detachEvent ? n.detachEvent("on" + t, i) : delete n[t]
        }
        ,
        n.prototype.innerHeight = function() {
            return "innerHeight"in window ? window.innerHeight : document.documentElement.clientHeight
        }
        ,
        n
    }();
    i = this.WeakMap || this.MozWeakMap || (i = function() {
        function n() {
            this.keys = [];
            this.values = []
        }
        return n.prototype.get = function(n) {
            var t, u, i, f, r;
            for (r = this.keys,
            t = i = 0,
            f = r.length; f > i; t = ++i)
                if (u = r[t],
                u === n)
                    return this.values[t]
        }
        ,
        n.prototype.set = function(n, t) {
            var i, f, r, e, u;
            for (u = this.keys,
            i = r = 0,
            e = u.length; e > r; i = ++r)
                if (f = u[i],
                f === n)
                    return void (this.values[i] = t);
            return this.keys.push(n),
            this.values.push(t)
        }
        ,
        n
    }());
    n = this.MutationObserver || this.WebkitMutationObserver || this.MozMutationObserver || (n = function() {
        function n() {
            "undefined" != typeof console && null !== console && console.warn("MutationObserver is not supported by your browser.");
            "undefined" != typeof console && null !== console && console.warn("WOW.js cannot detect dom mutations, please call .sync() after loading new content.")
        }
        return n.notSupported = !0,
        n.prototype.observe = function() {}
        ,
        n
    }());
    r = this.getComputedStyle || function(n) {
        return this.getPropertyValue = function(t) {
            var i;
            return "float" === t && (t = "styleFloat"),
            u.test(t) && t.replace(u, function(n, t) {
                return t.toUpperCase()
            }),
            (null != (i = n.currentStyle) ? i[t] : void 0) || null
        }
        ,
        this
    }
    ;
    u = /(\-([a-z]){1})/g;
    this.WOW = function() {
        function u(n) {
            null == n && (n = {});
            this.scrollCallback = t(this.scrollCallback, this);
            this.scrollHandler = t(this.scrollHandler, this);
            this.resetAnimation = t(this.resetAnimation, this);
            this.start = t(this.start, this);
            this.scrolled = !0;
            this.config = this.util().extend(n, this.defaults);
            null != n.scrollContainer && (this.config.scrollContainer = document.querySelector(n.scrollContainer));
            this.animationNameCache = new i;
            this.wowEvent = this.util().createEvent(this.config.boxClass)
        }
        return u.prototype.defaults = {
            boxClass: "wow",
            animateClass: "animated",
            offset: 0,
            mobile: !0,
            live: !0,
            callback: null,
            scrollContainer: null
        },
        u.prototype.init = function() {
            var n;
            return this.element = window.document.documentElement,
            "interactive" === (n = document.readyState) || "complete" === n ? this.start() : this.util().addEvent(document, "DOMContentLoaded", this.start),
            this.finished = []
        }
        ,
        u.prototype.start = function() {
            var t, i, u, r;
            if (this.stopped = !1,
            this.boxes = function() {
                var n, u, i, r;
                for (i = this.element.querySelectorAll("." + this.config.boxClass),
                r = [],
                n = 0,
                u = i.length; u > n; n++)
                    t = i[n],
                    r.push(t);
                return r
            }
            .call(this),
            this.all = function() {
                var n, u, i, r;
                for (i = this.boxes,
                r = [],
                n = 0,
                u = i.length; u > n; n++)
                    t = i[n],
                    r.push(t);
                return r
            }
            .call(this),
            this.boxes.length)
                if (this.disabled())
                    this.resetStyle();
                else
                    for (r = this.boxes,
                    i = 0,
                    u = r.length; u > i; i++)
                        t = r[i],
                        this.applyStyle(t, !0);
            return this.disabled() || (this.util().addEvent(this.config.scrollContainer || window, "scroll", this.scrollHandler),
            this.util().addEvent(window, "resize", this.scrollHandler),
            this.interval = setInterval(this.scrollCallback, 50)),
            this.config.live ? new n(function(n) {
                return function(t) {
                    var i, u, f, e, r;
                    for (r = [],
                    i = 0,
                    u = t.length; u > i; i++)
                        e = t[i],
                        r.push(function() {
                            var n, r, t, i;
                            for (t = e.addedNodes || [],
                            i = [],
                            n = 0,
                            r = t.length; r > n; n++)
                                f = t[n],
                                i.push(this.doSync(f));
                            return i
                        }
                        .call(n));
                    return r
                }
            }(this)).observe(document.body, {
                childList: !0,
                subtree: !0
            }) : void 0
        }
        ,
        u.prototype.stop = function() {
            return this.stopped = !0,
            this.util().removeEvent(this.config.scrollContainer || window, "scroll", this.scrollHandler),
            this.util().removeEvent(window, "resize", this.scrollHandler),
            null != this.interval ? clearInterval(this.interval) : void 0
        }
        ,
        u.prototype.sync = function() {
            if (n.notSupported)
                return this.doSync(this.element)
        }
        ,
        u.prototype.doSync = function(n) {
            var t, i, f, u, r;
            if (null == n && (n = this.element),
            1 === n.nodeType) {
                for (n = n.parentNode || n,
                u = n.querySelectorAll("." + this.config.boxClass),
                r = [],
                i = 0,
                f = u.length; f > i; i++)
                    t = u[i],
                    e.call(this.all, t) < 0 ? (this.boxes.push(t),
                    this.all.push(t),
                    this.stopped || this.disabled() ? this.resetStyle() : this.applyStyle(t, !0),
                    r.push(this.scrolled = !0)) : r.push(void 0);
                return r
            }
        }
        ,
        u.prototype.show = function(n) {
            return this.applyStyle(n),
            n.className = n.className + " " + this.config.animateClass,
            null != this.config.callback && this.config.callback(n),
            this.util().emitEvent(n, this.wowEvent),
            this.util().addEvent(n, "animationend", this.resetAnimation),
            this.util().addEvent(n, "oanimationend", this.resetAnimation),
            this.util().addEvent(n, "webkitAnimationEnd", this.resetAnimation),
            this.util().addEvent(n, "MSAnimationEnd", this.resetAnimation),
            n
        }
        ,
        u.prototype.applyStyle = function(n, t) {
            var i, r, u;
            return r = n.getAttribute("data-wow-duration"),
            i = n.getAttribute("data-wow-delay"),
            u = n.getAttribute("data-wow-iteration"),
            this.animate(function(f) {
                return function() {
                    return f.customStyle(n, t, r, i, u)
                }
            }(this))
        }
        ,
        u.prototype.animate = function() {
            return "requestAnimationFrame"in window ? function(n) {
                return window.requestAnimationFrame(n)
            }
            : function(n) {
                return n()
            }
        }(),
        u.prototype.resetStyle = function() {
            var r, n, u, t, i;
            for (t = this.boxes,
            i = [],
            n = 0,
            u = t.length; u > n; n++)
                r = t[n],
                i.push(r.style.visibility = "visible");
            return i
        }
        ,
        u.prototype.resetAnimation = function(n) {
            var t;
            if (n.type.toLowerCase().indexOf("animationend") >= 0)
                return (t = n.target || n.srcElement,
                t.className = t.className.replace(this.config.animateClass, "").trim())
        }
        ,
        u.prototype.customStyle = function(n, t, i, r, u) {
            return t && this.cacheAnimationName(n),
            n.style.visibility = t ? "hidden" : "visible",
            i && this.vendorSet(n.style, {
                animationDuration: i
            }),
            r && this.vendorSet(n.style, {
                animationDelay: r
            }),
            u && this.vendorSet(n.style, {
                animationIterationCount: u
            }),
            this.vendorSet(n.style, {
                animationName: t ? "none" : this.cachedAnimationName(n)
            }),
            n
        }
        ,
        u.prototype.vendors = ["moz", "webkit"],
        u.prototype.vendorSet = function(n, t) {
            var i, r, u, f;
            r = [];
            for (i in t)
                u = t[i],
                n["" + i] = u,
                r.push(function() {
                    var t, o, r, e;
                    for (r = this.vendors,
                    e = [],
                    t = 0,
                    o = r.length; o > t; t++)
                        f = r[t],
                        e.push(n["" + f + i.charAt(0).toUpperCase() + i.substr(1)] = u);
                    return e
                }
                .call(this));
            return r
        }
        ,
        u.prototype.vendorCSS = function(n, t) {
            var i, o, f, u, e, s;
            for (e = r(n),
            u = e.getPropertyCSSValue(t),
            f = this.vendors,
            i = 0,
            o = f.length; o > i; i++)
                s = f[i],
                u = u || e.getPropertyCSSValue("-" + s + "-" + t);
            return u
        }
        ,
        u.prototype.animationName = function(n) {
            var t;
            try {
                t = this.vendorCSS(n, "animation-name").cssText
            } catch (i) {
                t = r(n).getPropertyValue("animation-name")
            }
            return "none" === t ? "" : t
        }
        ,
        u.prototype.cacheAnimationName = function(n) {
            return this.animationNameCache.set(n, this.animationName(n))
        }
        ,
        u.prototype.cachedAnimationName = function(n) {
            return this.animationNameCache.get(n)
        }
        ,
        u.prototype.scrollHandler = function() {
            return this.scrolled = !0
        }
        ,
        u.prototype.scrollCallback = function() {
            var n;
            if (this.scrolled && !(this.scrolled = !1,
            this.boxes = function() {
                var t, u, i, r;
                for (i = this.boxes,
                r = [],
                t = 0,
                u = i.length; u > t; t++)
                    n = i[t],
                    n && (this.isVisible(n) ? this.show(n) : r.push(n));
                return r
            }
            .call(this),
            this.boxes.length || this.config.live))
                return this.stop()
        }
        ,
        u.prototype.offsetTop = function(n) {
            for (var t; void 0 === n.offsetTop; )
                n = n.parentNode;
            for (t = n.offsetTop; n = n.offsetParent; )
                t += n.offsetTop;
            return t
        }
        ,
        u.prototype.isVisible = function(n) {
            var r, u, t, f, i;
            return u = n.getAttribute("data-wow-offset") || this.config.offset,
            i = this.config.scrollContainer && this.config.scrollContainer.scrollTop || window.pageYOffset,
            f = i + Math.min(this.element.clientHeight, this.util().innerHeight()) - u,
            t = this.offsetTop(n),
            r = t + n.clientHeight,
            f >= t && r >= i
        }
        ,
        u.prototype.util = function() {
            return null != this._util ? this._util : this._util = new f
        }
        ,
        u.prototype.disabled = function() {
            return !this.config.mobile && this.util().isMobile(navigator.userAgent)
        }
        ,
        u
    }()
}
).call(this);
!function(n) {
    "function" == typeof define && define.amd ? define(["jquery"], n) : "object" == typeof exports ? module.exports = n : n(jQuery)
}(function(n) {
    function u(r) {
        var u = r || window.event, w = c.call(arguments, 1), l = 0, s = 0, e = 0, a = 0, b = 0, k = 0, v, y, p;
        if (r = n.event.fix(u),
        r.type = "mousewheel",
        "detail"in u && (e = -1 * u.detail),
        "wheelDelta"in u && (e = u.wheelDelta),
        "wheelDeltaY"in u && (e = u.wheelDeltaY),
        "wheelDeltaX"in u && (s = -1 * u.wheelDeltaX),
        "axis"in u && u.axis === u.HORIZONTAL_AXIS && (s = -1 * e,
        e = 0),
        l = 0 === e ? s : e,
        "deltaY"in u && (e = -1 * u.deltaY,
        l = e),
        "deltaX"in u && (s = u.deltaX,
        0 === e && (l = -1 * s)),
        0 !== e || 0 !== s)
            return 1 === u.deltaMode ? (v = n.data(this, "mousewheel-line-height"),
            l *= v,
            e *= v,
            s *= v) : 2 === u.deltaMode && (y = n.data(this, "mousewheel-page-height"),
            l *= y,
            e *= y,
            s *= y),
            (a = Math.max(Math.abs(e), Math.abs(s)),
            (!t || t > a) && (t = a,
            o(u, a) && (t /= 40)),
            o(u, a) && (l /= 40,
            s /= 40,
            e /= 40),
            l = Math[l >= 1 ? "floor" : "ceil"](l / t),
            s = Math[s >= 1 ? "floor" : "ceil"](s / t),
            e = Math[e >= 1 ? "floor" : "ceil"](e / t),
            i.settings.normalizeOffset && this.getBoundingClientRect) && (p = this.getBoundingClientRect(),
            b = r.clientX - p.left,
            k = r.clientY - p.top),
            r.deltaX = s,
            r.deltaY = e,
            r.deltaFactor = t,
            r.offsetX = b,
            r.offsetY = k,
            r.deltaMode = 0,
            w.unshift(r, l, s, e),
            f && clearTimeout(f),
            f = setTimeout(h, 200),
            (n.event.dispatch || n.event.handle).apply(this, w)
    }
    function h() {
        t = null
    }
    function o(n, t) {
        return i.settings.adjustOldDeltas && "mousewheel" === n.type && t % 120 == 0
    }
    var f, t, s = ["wheel", "mousewheel", "DOMMouseScroll", "MozMousePixelScroll"], r = "onwheel"in document || document.documentMode >= 9 ? ["wheel"] : ["mousewheel", "DomMouseScroll", "MozMousePixelScroll"], c = Array.prototype.slice, e, i;
    if (n.event.fixHooks)
        for (e = s.length; e; )
            n.event.fixHooks[s[--e]] = n.event.mouseHooks;
    i = n.event.special.mousewheel = {
        version: "3.1.12",
        setup: function() {
            if (this.addEventListener)
                for (var t = r.length; t; )
                    this.addEventListener(r[--t], u, !1);
            else
                this.onmousewheel = u;
            n.data(this, "mousewheel-line-height", i.getLineHeight(this));
            n.data(this, "mousewheel-page-height", i.getPageHeight(this))
        },
        teardown: function() {
            if (this.removeEventListener)
                for (var t = r.length; t; )
                    this.removeEventListener(r[--t], u, !1);
            else
                this.onmousewheel = null;
            n.removeData(this, "mousewheel-line-height");
            n.removeData(this, "mousewheel-page-height")
        },
        getLineHeight: function(t) {
            var r = n(t)
              , i = r["offsetParent"in n.fn ? "offsetParent" : "parent"]();
            return i.length || (i = n("body")),
            parseInt(i.css("fontSize"), 10) || parseInt(r.css("fontSize"), 10) || 16
        },
        getPageHeight: function(t) {
            return n(t).height()
        },
        settings: {
            adjustOldDeltas: !0,
            normalizeOffset: !0
        }
    };
    n.fn.extend({
        mousewheel: function(n) {
            return n ? this.bind("mousewheel", n) : this.trigger("mousewheel")
        },
        unmousewheel: function(n) {
            return this.unbind("mousewheel", n)
        }
    })
}),
function(n) {
    typeof define == "function" && define.amd && define.amd.jQuery ? define(["jquery"], n) : typeof module != "undefined" && module.exports ? n(require("jquery")) : n(jQuery)
}(function(n) {
    function ft(t) {
        return t && t.allowPageScroll === undefined && (t.swipe !== undefined || t.swipeStatus !== undefined) && (t.allowPageScroll = p),
        t.click !== undefined && t.tap === undefined && (t.tap = t.click),
        t || (t = {}),
        t = n.extend({}, n.fn.swipe.defaults, t),
        this.each(function() {
            var r = n(this)
              , i = r.data(h);
            i || (i = new et(this,t),
            r.data(h, i))
        })
    }
    function et(ft, et) {
        function tr(t) {
            if (!pu() && !(n(t.target).closest(et.excludedElements, ot).length > 0)) {
                var r = t.originalEvent ? t.originalEvent : t, f, u = r.touches, e = u ? u[0] : r;
                return (st = rt,
                u ? ct = u.length : et.preventDefaultEvents !== !1 && t.preventDefault(),
                at = 0,
                vt = null,
                yt = null,
                kt = null,
                lt = 0,
                gt = 0,
                ni = 0,
                pt = 1,
                bt = 0,
                li = ku(),
                dr(),
                wi(0, e),
                !u || ct === et.fingers || et.fingers === l || oi() ? (gi = ii(),
                ct == 2 && (wi(1, u[1]),
                gt = ni = cr(ht[0].start, ht[1].start)),
                (et.swipeStatus || et.pinchStatus) && (f = wt(r, st))) : f = !1,
                f === !1) ? (st = i,
                wt(r, st),
                f) : (et.hold && (ei = setTimeout(n.proxy(function() {
                    ot.trigger("hold", [r.target]);
                    et.hold && (f = et.hold.call(ot, r, r.target))
                }, this), et.longTapThreshold)),
                pi(!0),
                null)
            }
        }
        function ir(n) {
            var f = n.originalEvent ? n.originalEvent : n, e, h;
            if (st !== t && st !== i && !yi()) {
                var s, r = f.touches, c = r ? r[0] : f, u = gr(c);
                ai = ii();
                r && (ct = r.length);
                et.hold && clearTimeout(ei);
                st = o;
                ct == 2 && (gt == 0 ? (wi(1, r[1]),
                gt = ni = cr(ht[0].start, ht[1].start)) : (gr(r[1]),
                ni = cr(ht[0].end, ht[1].end),
                kt = gu(ht[0].end, ht[1].end)),
                pt = du(gt, ni),
                bt = Math.abs(gt - ni));
                ct === et.fingers || et.fingers === l || !r || oi() ? (vt = iu(u.start, u.end),
                yt = iu(u.last, u.end),
                uu(n, yt),
                at = nf(u.start, u.end),
                lt = tu(),
                bu(vt, at),
                s = wt(f, st),
                (!et.triggerOnTouchEnd || et.triggerOnTouchLeave) && (e = !0,
                et.triggerOnTouchLeave && (h = rf(this),
                e = uf(u.end, h)),
                !et.triggerOnTouchEnd && e ? st = fr(o) : et.triggerOnTouchLeave && !e && (st = fr(t)),
                (st == i || st == t) && wt(f, st))) : (st = i,
                wt(f, st));
                s === !1 && (st = i,
                wt(f, st))
            }
        }
        function rr(n) {
            var r = n.originalEvent ? n.originalEvent : n
              , u = r.touches;
            if (u) {
                if (u.length && !yi())
                    return yu(r),
                    !0;
                if (u.length && yi())
                    return !0
            }
            return yi() && (ct = nr),
            ai = ii(),
            lt = tu(),
            or() || !er() ? (st = i,
            wt(r, st)) : et.triggerOnTouchEnd || et.triggerOnTouchEnd == !1 && st === o ? (et.preventDefaultEvents !== !1 && n.preventDefault(),
            st = t,
            wt(r, st)) : !et.triggerOnTouchEnd && br() ? (st = t,
            dt(r, st, k)) : st === o && (st = i,
            wt(r, st)),
            pi(!1),
            null
        }
        function ui() {
            ct = 0;
            ai = 0;
            gi = 0;
            gt = 0;
            ni = 0;
            pt = 1;
            dr();
            pi(!1)
        }
        function ur(n) {
            var i = n.originalEvent ? n.originalEvent : n;
            et.triggerOnTouchLeave && (st = fr(t),
            wt(i, st))
        }
        function lr() {
            ot.unbind(hi, tr);
            ot.unbind(ci, ui);
            ot.unbind(ki, ir);
            ot.unbind(di, rr);
            ri && ot.unbind(ri, ur);
            pi(!1)
        }
        function fr(n) {
            var r = n
              , f = ar()
              , u = er()
              , e = or();
            return !f || e ? r = i : u && n == o && (!et.triggerOnTouchEnd || et.triggerOnTouchLeave) ? r = t : !u && n == t && et.triggerOnTouchLeave && (r = i),
            r
        }
        function wt(n, r) {
            var u, f = n.touches;
            return (eu() || sr()) && (u = dt(n, r, w)),
            (fu() || oi()) && u !== !1 && (u = dt(n, r, b)),
            au() && u !== !1 ? u = dt(n, r, tt) : vu() && u !== !1 ? u = dt(n, r, it) : lu() && u !== !1 && (u = dt(n, r, k)),
            r === i && (sr() && (u = dt(n, r, w)),
            oi() && (u = dt(n, r, b)),
            ui(n)),
            r === t && (f ? f.length || ui(n) : ui(n)),
            u
        }
        function dt(o, s, h) {
            var c;
            if (h == w) {
                if (ot.trigger("swipeStatus", [s, vt || null, at || 0, lt || 0, ct, ht, yt]),
                et.swipeStatus && (c = et.swipeStatus.call(ot, o, s, vt || null, at || 0, lt || 0, ct, ht, yt),
                c === !1))
                    return !1;
                if (s == t && yr()) {
                    if (clearTimeout(fi),
                    clearTimeout(ei),
                    ot.trigger("swipe", [vt, at, lt, ct, ht, yt]),
                    et.swipe && (c = et.swipe.call(ot, o, vt, at, lt, ct, ht, yt),
                    c === !1))
                        return !1;
                    switch (vt) {
                    case r:
                        ot.trigger("swipeLeft", [vt, at, lt, ct, ht, yt]);
                        et.swipeLeft && (c = et.swipeLeft.call(ot, o, vt, at, lt, ct, ht, yt));
                        break;
                    case u:
                        ot.trigger("swipeRight", [vt, at, lt, ct, ht, yt]);
                        et.swipeRight && (c = et.swipeRight.call(ot, o, vt, at, lt, ct, ht, yt));
                        break;
                    case f:
                        ot.trigger("swipeUp", [vt, at, lt, ct, ht, yt]);
                        et.swipeUp && (c = et.swipeUp.call(ot, o, vt, at, lt, ct, ht, yt));
                        break;
                    case e:
                        ot.trigger("swipeDown", [vt, at, lt, ct, ht, yt]);
                        et.swipeDown && (c = et.swipeDown.call(ot, o, vt, at, lt, ct, ht, yt))
                    }
                }
            }
            if (h == b) {
                if (ot.trigger("pinchStatus", [s, kt || null, bt || 0, lt || 0, ct, pt, ht]),
                et.pinchStatus && (c = et.pinchStatus.call(ot, o, s, kt || null, bt || 0, lt || 0, ct, pt, ht),
                c === !1))
                    return !1;
                if (s == t && vr())
                    switch (kt) {
                    case v:
                        ot.trigger("pinchIn", [kt || null, bt || 0, lt || 0, ct, pt, ht]);
                        et.pinchIn && (c = et.pinchIn.call(ot, o, kt || null, bt || 0, lt || 0, ct, pt, ht));
                        break;
                    case y:
                        ot.trigger("pinchOut", [kt || null, bt || 0, lt || 0, ct, pt, ht]);
                        et.pinchOut && (c = et.pinchOut.call(ot, o, kt || null, bt || 0, lt || 0, ct, pt, ht))
                    }
            }
            return h == k ? (s === i || s === t) && (clearTimeout(fi),
            clearTimeout(ei),
            hr() && !su() ? (ti = ii(),
            fi = setTimeout(n.proxy(function() {
                ti = null;
                ot.trigger("tap", [o.target]);
                et.tap && (c = et.tap.call(ot, o, o.target))
            }, this), et.doubleTapThreshold)) : (ti = null,
            ot.trigger("tap", [o.target]),
            et.tap && (c = et.tap.call(ot, o, o.target)))) : h == tt ? (s === i || s === t) && (clearTimeout(fi),
            clearTimeout(ei),
            ti = null,
            ot.trigger("doubletap", [o.target]),
            et.doubleTap && (c = et.doubleTap.call(ot, o, o.target))) : h == it && (s === i || s === t) && (clearTimeout(fi),
            ti = null,
            ot.trigger("longtap", [o.target]),
            et.longTap && (c = et.longTap.call(ot, o, o.target))),
            c
        }
        function er() {
            var n = !0;
            return et.threshold !== null && (n = at >= et.threshold),
            n
        }
        function or() {
            var n = !1;
            return et.cancelThreshold !== null && vt !== null && (n = nu(vt) - at >= et.cancelThreshold),
            n
        }
        function ru() {
            return et.pinchThreshold !== null ? bt >= et.pinchThreshold : !0
        }
        function ar() {
            return et.maxTimeThreshold ? lt >= et.maxTimeThreshold ? !1 : !0 : !0
        }
        function uu(n, t) {
            if (et.preventDefaultEvents !== !1)
                if (et.allowPageScroll === p)
                    n.preventDefault();
                else {
                    var i = et.allowPageScroll === nt;
                    switch (t) {
                    case r:
                        (et.swipeLeft && i || !i && et.allowPageScroll != d) && n.preventDefault();
                        break;
                    case u:
                        (et.swipeRight && i || !i && et.allowPageScroll != d) && n.preventDefault();
                        break;
                    case f:
                        (et.swipeUp && i || !i && et.allowPageScroll != g) && n.preventDefault();
                        break;
                    case e:
                        (et.swipeDown && i || !i && et.allowPageScroll != g) && n.preventDefault()
                    }
                }
        }
        function vr() {
            var n = pr()
              , t = wr()
              , i = ru();
            return n && t && i
        }
        function oi() {
            return !!(et.pinchStatus || et.pinchIn || et.pinchOut)
        }
        function fu() {
            return !!(vr() && oi())
        }
        function yr() {
            var n = ar()
              , t = er()
              , i = pr()
              , r = wr()
              , u = or();
            return !u && r && i && t && n
        }
        function sr() {
            return !!(et.swipe || et.swipeStatus || et.swipeLeft || et.swipeRight || et.swipeUp || et.swipeDown)
        }
        function eu() {
            return !!(yr() && sr())
        }
        function pr() {
            return ct === et.fingers || et.fingers === l || !c
        }
        function wr() {
            return ht[0].end.x !== 0
        }
        function br() {
            return !!et.tap
        }
        function hr() {
            return !!et.doubleTap
        }
        function ou() {
            return !!et.longTap
        }
        function kr() {
            if (ti == null)
                return !1;
            var n = ii();
            return hr() && n - ti <= et.doubleTapThreshold
        }
        function su() {
            return kr()
        }
        function hu() {
            return (ct === 1 || !c) && (isNaN(at) || at < et.threshold)
        }
        function cu() {
            return lt > et.longTapThreshold && at < ut
        }
        function lu() {
            return !!(hu() && br())
        }
        function au() {
            return !!(kr() && hr())
        }
        function vu() {
            return !!(cu() && ou())
        }
        function yu(n) {
            vi = ii();
            nr = n.touches.length + 1
        }
        function dr() {
            vi = 0;
            nr = 0
        }
        function yi() {
            var n = !1, t;
            return vi && (t = ii() - vi,
            t <= et.fingerReleaseThreshold && (n = !0)),
            n
        }
        function pu() {
            return !!(ot.data(h + "_intouch") === !0)
        }
        function pi(n) {
            ot && (n === !0 ? (ot.bind(ki, ir),
            ot.bind(di, rr),
            ri && ot.bind(ri, ur)) : (ot.unbind(ki, ir, !1),
            ot.unbind(di, rr, !1),
            ri && ot.unbind(ri, ur, !1)),
            ot.data(h + "_intouch", n === !0))
        }
        function wi(n, t) {
            var i = {
                start: {
                    x: 0,
                    y: 0
                },
                last: {
                    x: 0,
                    y: 0
                },
                end: {
                    x: 0,
                    y: 0
                }
            };
            return i.start.x = i.last.x = i.end.x = t.pageX || t.clientX,
            i.start.y = i.last.y = i.end.y = t.pageY || t.clientY,
            ht[n] = i,
            i
        }
        function gr(n) {
            var i = n.identifier !== undefined ? n.identifier : 0
              , t = wu(i);
            return t === null && (t = wi(i, n)),
            t.last.x = t.end.x,
            t.last.y = t.end.y,
            t.end.x = n.pageX || n.clientX,
            t.end.y = n.pageY || n.clientY,
            t
        }
        function wu(n) {
            return ht[n] || null
        }
        function bu(n, t) {
            t = Math.max(t, nu(n));
            li[n].distance = t
        }
        function nu(n) {
            return li[n] ? li[n].distance : undefined
        }
        function ku() {
            var n = {};
            return n[r] = bi(r),
            n[u] = bi(u),
            n[f] = bi(f),
            n[e] = bi(e),
            n
        }
        function bi(n) {
            return {
                direction: n,
                distance: 0
            }
        }
        function tu() {
            return ai - gi
        }
        function cr(n, t) {
            var i = Math.abs(n.x - t.x)
              , r = Math.abs(n.y - t.y);
            return Math.round(Math.sqrt(i * i + r * r))
        }
        function du(n, t) {
            var i = t / n * 1;
            return i.toFixed(2)
        }
        function gu() {
            return pt < 1 ? y : v
        }
        function nf(n, t) {
            return Math.round(Math.sqrt(Math.pow(t.x - n.x, 2) + Math.pow(t.y - n.y, 2)))
        }
        function tf(n, t) {
            var r = n.x - t.x
              , u = t.y - n.y
              , f = Math.atan2(u, r)
              , i = Math.round(f * 180 / Math.PI);
            return i < 0 && (i = 360 - Math.abs(i)),
            i
        }
        function iu(n, t) {
            var i = tf(n, t);
            return i <= 45 && i >= 0 ? r : i <= 360 && i >= 315 ? r : i >= 135 && i <= 225 ? u : i > 45 && i < 135 ? e : f
        }
        function ii() {
            var n = new Date;
            return n.getTime()
        }
        function rf(t) {
            t = n(t);
            var i = t.offset();
            return {
                left: i.left,
                right: i.left + t.outerWidth(),
                top: i.top,
                bottom: i.top + t.outerHeight()
            }
        }
        function uf(n, t) {
            return n.x > t.left && n.x < t.right && n.y > t.top && n.y < t.bottom
        }
        var et = n.extend({}, et)
          , si = c || s || !et.fallbackToMouseEvents
          , hi = si ? s ? a ? "MSPointerDown" : "pointerdown" : "touchstart" : "mousedown"
          , ki = si ? s ? a ? "MSPointerMove" : "pointermove" : "touchmove" : "mousemove"
          , di = si ? s ? a ? "MSPointerUp" : "pointerup" : "touchend" : "mouseup"
          , ri = si ? s ? "mouseleave" : null : "mouseleave"
          , ci = s ? a ? "MSPointerCancel" : "pointercancel" : "touchcancel"
          , at = 0
          , vt = null
          , yt = null
          , lt = 0
          , gt = 0
          , ni = 0
          , pt = 1
          , bt = 0
          , kt = 0
          , li = null
          , ot = n(ft)
          , st = "start"
          , ct = 0
          , ht = {}
          , gi = 0
          , ai = 0
          , vi = 0
          , nr = 0
          , ti = 0
          , fi = null
          , ei = null;
        try {
            ot.bind(hi, tr);
            ot.bind(ci, ui)
        } catch (ff) {
            n.error("events not supported " + hi + "," + ci + " on jQuery.swipe")
        }
        this.enable = function() {
            return ot.bind(hi, tr),
            ot.bind(ci, ui),
            ot
        }
        ;
        this.disable = function() {
            return lr(),
            ot
        }
        ;
        this.destroy = function() {
            lr();
            ot.data(h, null);
            ot = null
        }
        ;
        this.option = function(t, i) {
            if (typeof t == "object")
                et = n.extend(et, t);
            else if (et[t] !== undefined) {
                if (i === undefined)
                    return et[t];
                et[t] = i
            } else if (t)
                n.error("Option " + t + " does not exist on jQuery.swipe.options");
            else
                return et;
            return null
        }
    }
    var r = "left"
      , u = "right"
      , f = "up"
      , e = "down"
      , v = "in"
      , y = "out"
      , p = "none"
      , nt = "auto"
      , w = "swipe"
      , b = "pinch"
      , k = "tap"
      , tt = "doubletap"
      , it = "longtap"
      , d = "horizontal"
      , g = "vertical"
      , l = "all"
      , ut = 10
      , rt = "start"
      , o = "move"
      , t = "end"
      , i = "cancel"
      , c = "ontouchstart"in window
      , a = window.navigator.msPointerEnabled && !window.navigator.pointerEnabled && !c
      , s = (window.navigator.pointerEnabled || window.navigator.msPointerEnabled) && !c
      , h = "TouchSwipe";
    n.fn.swipe = function(t) {
        var r = n(this)
          , i = r.data(h);
        if (i && typeof t == "string") {
            if (i[t])
                return i[t].apply(this, Array.prototype.slice.call(arguments, 1));
            n.error("Method " + t + " does not exist on jQuery.swipe")
        } else if (i && typeof t == "object")
            i.option.apply(this, arguments);
        else if (!i && (typeof t == "object" || !t))
            return ft.apply(this, arguments);
        return r
    }
    ;
    n.fn.swipe.version = "1.6.15";
    n.fn.swipe.defaults = {
        fingers: 1,
        threshold: 75,
        cancelThreshold: null,
        pinchThreshold: 20,
        maxTimeThreshold: null,
        fingerReleaseThreshold: 250,
        longTapThreshold: 500,
        doubleTapThreshold: 200,
        swipe: null,
        swipeLeft: null,
        swipeRight: null,
        swipeUp: null,
        swipeDown: null,
        swipeStatus: null,
        pinchIn: null,
        pinchOut: null,
        pinchStatus: null,
        click: null,
        tap: null,
        doubleTap: null,
        longTap: null,
        hold: null,
        triggerOnTouchEnd: !0,
        triggerOnTouchLeave: !1,
        allowPageScroll: "auto",
        fallbackToMouseEvents: !0,
        excludedElements: "label, button, input, select, textarea, a, .noSwipe",
        preventDefaultEvents: !0
    };
    n.fn.swipe.phases = {
        PHASE_START: rt,
        PHASE_MOVE: o,
        PHASE_END: t,
        PHASE_CANCEL: i
    };
    n.fn.swipe.directions = {
        LEFT: r,
        RIGHT: u,
        UP: f,
        DOWN: e,
        IN: v,
        OUT: y
    };
    n.fn.swipe.pageScroll = {
        NONE: p,
        HORIZONTAL: d,
        VERTICAL: g,
        AUTO: nt
    };
    n.fn.swipe.fingers = {
        ONE: 1,
        TWO: 2,
        THREE: 3,
        FOUR: 4,
        FIVE: 5,
        ALL: l
    }
});
$(document).ready(function() {
    $(".navbar-shiguang .navbar-respond li a, a.toscroll").bind("click", function(n) {
        var t = $(this)
          , i = t.attr("href");
        $("html,body").stop().animate({
            scrollTop: $(i).offset().top
        });
        n.preventDefault()
    });
    $("#home .carousel").on("slide.bs.carousel", function() {
        var n = $(this);
        n.find(".carousel-inner .item .wow:not(.animated)").addClass("animated")
    });
    $('[data-spy="scroll"]').on("activate.bs.scrollspy", function() {
        $(".navbar-shiguang").attr("data-nav", $(".navbar-shiguang .nav li.active a").attr("href"))
    });
    $(window).scroll(function() {
        $(this).scrollTop() >= 200 ? $(".scroll-top").fadeIn(180) : $(".scroll-top").fadeOut(180)
    });
    new WOW({
        offset: 0
    }).init();
    var n = function(n) {
        var t = $(".navbar-shiguang .navbar-respond li.active")
          , i = n ? t.prev() : t.next();
        i && i.find("a").click()
    };
    $(window).resize(function() {
        $(".navbar-shiguang .navbar-respond li.active a").click()
    });
    $("body").mousewheel(function(t) {
        n(t.deltaY > 0)
    });
    $("body").swipe({
        swipe: function(t, i) {
            i.toString() == "up" && n(!1);
            i.toString() == "down" && n(!0)
        }
    });
    $("section#home .carousel").swipe({
        swipe: function(n, t) {
            t.toString() == "right" && $(".carousel").carousel("prev");
            t.toString() == "left" && $(".carousel").carousel("next")
        }
    });
    $("section#abouts").swipe({
        swipe: function(n, t) {
            var i = function(n) {
                var t = ($("#abouts ul.nav-tabs li.active").index() + n) % $("#abouts .nav-tabs li").length;
                $("#abouts .nav-tabs li:eq(" + t + ") a").tab("show")
            };
            t.toString() == "left" && i(1);
            t.toString() == "right" && i(-1)
        }
    }),
    function() {
        try {
            window.console && window.console.log && (console.log("\n            %c####################\n           %c#.%c*#################\n          %c####.%c*##############\n         %c#######.%c*###########\n        %c*########.%c*#########\n        %c###########.\n         %c############.\n          %c.############\n            %c.###########\n    %c#########*%c.########*\n   %c############*%c.######\n  %c###############*%c.###\n %c##################%c.#\n%c####################    %c时光网络科技\n\n客户至上   尊重个人   鼓励创新   团队合作\n\n", "color:#0f6fa4", "color:#e94f18", "color:#0f6fa4", "color:#e94f18", "color:#0f6fa4", "color:#e94f18", "color:#0f6fa4", "color:#e94f18", "color:#0f6fa4", "color:#e94f18", "color:#e94f18", "color:#e94f18", "color:#e94f18", "color:#0f6fa4", "color:#e94f18", "color:#0f6fa4", "color:#e94f18", "color:#0f6fa4", "color:#e94f18", "color:#0f6fa4", "color:#e94f18", "color:#0f6fa4", "color:#e94f18"),
            console.log("请将简历发送至：%cjobs@5shiguang.net（邮件标题请以“姓名-应聘xx职位-来自xx）", "color:#e94f18"),
            console.log("职位介绍：http://www.5shiguang.net/jobs"))
        } catch (n) {}
    }()
})
