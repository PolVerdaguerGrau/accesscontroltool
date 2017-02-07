/*
 * (C) Copyright 2015 Netcentric AG.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package biz.netcentric.cq.tools.actool.configmodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import biz.netcentric.cq.tools.actool.dumpservice.AcDumpElement;
import biz.netcentric.cq.tools.actool.dumpservice.AcDumpElementVisitor;
import biz.netcentric.cq.tools.actool.helper.Constants;

public class AuthorizableConfigBean implements AcDumpElement {

    private String principalID;
    private String principalName;
    private String description;

    private String[] memberOf;
    private String memberOfStringFromConfig;

//old    private String[] honors;
    private String honorsStringFromConfig;

    private String[] members;
    private String membersStringFromConfig;

    private String path;
    private String password;

    private String profileContent;
    private String preferencesContent;
    
    private String migrateFrom;

    private boolean isGroup = true;
    private boolean isSystemUser = false;

    public String getPrincipalID() {
        return principalID;
    }

    public void setPrincipalID(final String principalID) {
        this.principalID = principalID;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(final String principalName) {
        this.principalName = principalName;
    }


    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
    
    public String getProfileContent() {
        return profileContent;
    }

    public void setProfileContent(String profileContent) {
        this.profileContent = profileContent;
    }

    public String getPreferencesContent() {
        return preferencesContent;
    }

    public void setPreferencesContent(String preferencesContent) {
        this.preferencesContent = preferencesContent;
    }

    public void setMemberOfString(final String memberOfString) {
        memberOfStringFromConfig = memberOfString;
    }

    public void setHonorsString(final String honorsString) { honorsStringFromConfig = honorsString;}

    public void setMembersString(final String membersString) {
        membersStringFromConfig = membersString;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setIsGroup(final boolean isGroup) {
        this.isGroup = isGroup;
    }

    public boolean isSystemUser() {
        return isSystemUser;
    }

    public void setIsSystemUser(final boolean isSystemUser) {
        this.isSystemUser = isSystemUser;
    }

    public String[] getMemberOf() {
        return memberOf;
    }

//old    public String[] getHonors() { return honors; }

    public boolean isMemberOfOtherGroups() {
        return memberOf != null;
    }

    public String getMemberOfStringFromConfig() {
        return memberOfStringFromConfig;
    }
    public String getHonorsStringFromConfig() { return honorsStringFromConfig; }

    public String getMemberOfString() {
        if (memberOf == null) {
            return "";
        }

        final StringBuilder memberOfString = new StringBuilder();

        for (final String group : memberOf) {
            memberOfString.append(group).append(",");
        }
        return StringUtils.chop(memberOfString.toString());
    }

/*  old  public String getHonorsString() {
        if (honors == null) {
            return "";
        }

        final StringBuilder honorsString = new StringBuilder();

        for (final String honorPath : honors) {
            honorsString.append(honorPath).append(",");
        }
        return StringUtils.chomp(honorsString.toString());
    }
*/
    public void setMemberOf(final String[] memberOf) {
        this.memberOf = memberOf;
    }
// old   public void setHonors(final String[] honors) {this.honors = honors; }

    public void setMemberOf(final List<String> memberOf) {
        if ((memberOf != null) && !memberOf.isEmpty()) {
            this.memberOf = memberOf.toArray(new String[memberOf.size()]);
        }
    }

 /*old   public void setHonors(final List<String> honors) {
        if ((honors != null) && !honors.isEmpty()) {
            this.honors = honors.toArray(new String[honors.size()]);
        }
    }
*/
    public void addMemberOf(final String member) {
        if (memberOf == null) {
            memberOf = new String[] { member };
            return;
        }
        final List<String> memberList = new ArrayList<String>();
        memberList.addAll(Arrays.asList(memberOf));
        if (!memberList.contains(member)) {
            memberList.add(member);
            memberOf = memberList.toArray(new String[memberList.size()]);
        }
    }

 /*old   public void addHonors(final String honor) {
        if(honors == null) {
            honors = new String[] { honor };
            return;
        }
        final List<String> honorList = new ArrayList<String>();
        honorList.addAll(Arrays.asList(honors));
        if (!honorList.contains(honor)) {
            honorList.add(honor);
            honors = honorList.toArray(new String[honorList.size()]);
        }
    }*/

    public String getMembersStringFromConfig() {
        return membersStringFromConfig;
    }

    public String getMembersString() {
        if (members == null) {
            return "";
        }

        final StringBuilder membersString = new StringBuilder();

        for (final String group : members) {
            membersString.append(group).append(",");
        }
        return StringUtils.chop(membersString.toString());
    }

    public String[] getMembers() {
        return members;
    }

    public void setMembers(final String[] members) {
        this.members = members;
    }


    public String getMigrateFrom() {
        return migrateFrom;
    }

    /** Set a group name, from which the users are taken over to this group. The group given is deleted after the run. This property is only
     * to be used temporarily (usually only included in one released version that travels all environments, once all groups are migrated the
     * config should be removed). If not set (the default) nothing happens. If the property points to a group that does not exist (anymore),
     * the property is ignored.
     * 
     * @param migrateFrom */
    public void setMigrateFrom(String migrateFrom) {
        this.migrateFrom = migrateFrom;
    }

    public boolean membersContainsAnonymous() {
        return ArrayUtils.contains(members, Constants.USER_ANONYMOUS);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\n" + "id: " + principalID + "\n");
        sb.append("name: " + principalName + "\n");
        sb.append("path: " + path + "\n");
        sb.append("isMemberOf: " + getMemberOfString() + "\n");
        sb.append("members: " + getMembersString() + "\n");
        return sb.toString();
    }

    @Override
    public void accept(final AcDumpElementVisitor acDumpElementVisitor) {
        acDumpElementVisitor.visit(this);
    }

    // --- only for junit test (TODO: check if test can be refactored to not require

    private String assertedExceptionString = null;

    public String getAssertedExceptionString() {
        return assertedExceptionString;
    }

    public void setAssertedExceptionString(final String assertedException) {
        assertedExceptionString = assertedException;
    }

}
