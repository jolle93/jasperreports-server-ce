define(["require","exports","module","runtime_dependencies/js-sdk/src/jrs.configs"],function(e,t,n){var r=e("runtime_dependencies/js-sdk/src/jrs.configs"),o={currentContext:"default",displayWebHelp:function(){var e=r.webHelpModuleState;if(e){var t=e.hostURL+"/"+e.pagePrefix+o.getPageForContext();window.name="";window.open(t,"MCWebHelp").focus()}},getPageForContext:function(){return r.webHelpModuleState.contextMap[o.currentContext]},setCurrentContext:function(e){o.currentContext=e}};n.exports=o});