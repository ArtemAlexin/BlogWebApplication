<#-- @ftlvariable name="message" type="String" -->
<#import "../common.ftlh" as c>
<@c.specialPage>
    <#list errorMessage as e>
        <h3>
            ${e}
        </h3>
    </#list>
</@c.specialPage>
