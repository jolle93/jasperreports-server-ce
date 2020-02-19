define(["require","exports","module","underscore","jquery","../factory/importModelAttributesFactory","../enum/brokenDependencyStrategyEnum","runtime_dependencies/js-sdk/src/common/transport/AjaxFormSubmitter","runtime_dependencies/js-sdk/src/common/extension/backboneValidationExtension","runtime_dependencies/js-sdk/src/common/model/BaseModel","../enum/secureKeyTypeEnum"],function(e,t,r){var i=e("underscore"),n=e("jquery"),s=e("../factory/importModelAttributesFactory"),o=e("../enum/brokenDependencyStrategyEnum"),u=e("runtime_dependencies/js-sdk/src/common/transport/AjaxFormSubmitter"),d=e("runtime_dependencies/js-sdk/src/common/extension/backboneValidationExtension"),c=e("runtime_dependencies/js-sdk/src/common/model/BaseModel"),a=e("../enum/secureKeyTypeEnum"),m=c.extend({defaults:{fileName:"",update:!0,skipUserUpdate:!1,mergeOrganization:!1,skipThemes:!0,keyType:"",secretKey:"",secretUri:"",invalidKeyError:"",invalidSecureFileContentError:"",brokenDependencies:o.FAIL},url:"rest_v2/import",validation:{fileName:[{fn:function(e){return!/\.zip$/.test(e)}}]},initialize:function(e,t){this.form=new u(t.form,this.url,"post","multipart/form-data")},parse:function(){return this.attributes},save:function(){var e,t,r=this;return this.isNew()?(e=new n.Deferred,this.form.submit().done(function(t){r.set("id",t.id),i.isUndefined(t.errorCode)&&i.isUndefined(t.error)?e.resolve(t):e.reject(t)}).fail(function(t){r.trigger("error",t),e.reject(t)})):(t=this._convertParameters(),e=c.prototype.save.call(this,{parameters:t},{url:this.url+"/"+this.id}).fail(i.bind(this.trigger,this,"error"))),e},cancel:function(){var e=this.url+"/"+this.id;this.destroy({url:e})},reset:function(e,t){var r=i.extend({},this.defaults,s(e),t);this.clear().set(i.extend({},r)),this.id=void 0},_convertParameters:function(){var e=[];this.get("skipUserUpdate")&&e.push("skip-user-update"),this.get("includeAccessEvents")&&e.push("include-access-events"),this.get("includeAuditEvents")&&e.push("include-audit-events"),this.get("includeMonitoringEvents")&&e.push("include-monitoring-events"),this.get("includeServerSettings")&&e.push("include-server-setting"),this.get("mergeOrganization")&&e.push("merge-organization"),this.get("skipThemes")&&e.push("skip-themes"),this.get("update")&&e.push("update");var t=this.get("keyType");return t&&(e.push("keyType"),t===a.VALUE&&e.push("secret-key"),t===a.FILE&&e.push("secretUri")),e}});i.extend(m.prototype,d.mixin),r.exports=m});