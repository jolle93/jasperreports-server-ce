define(["require","exports","module","bundle!AttributesBundle","underscore"],function(e,r,t){function s(e,r){var t={};return n.each(e,function(e,s){n.isObject(e)?t[e.field]=e.fn(r[s]):t[e]=r[s]}),t}function i(e){return a["attributes.attribute.permissionMask."+e]}var a=e("bundle!AttributesBundle"),n=e("underscore"),u={},o=[a["attributes.error.message.unknown.error"]];u["access.denied"]=[a["attributes.error.message.access.denied"],["name"]],u[401]=[a["attributes.error.message.not.authenticated"]],u["illegal.parameter.value.error"]=[a["attributes.illegal.parameter.value.error"]],u["attribute.invalid.permission.order"]=[a["attributes.error.message.invalid.permission.order"],["name",{field:"strongerPermission",fn:i},{field:"inheritedPermission",fn:i}]],u["attribute.duplicate.server.level"]=[a["attributes.error.message.duplicate.server.level"],["name"]],t.exports=function(e){var r=e.responseJSON||{},t=r.errorCode,i=r.parameters,a=e.status,l=u[t||a]||o;return n.template(l[0])(l[1]?s(l[1],i):{})}});