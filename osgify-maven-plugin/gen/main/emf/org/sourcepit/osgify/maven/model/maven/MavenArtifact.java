/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.maven.model.maven;

import java.io.File;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Maven Artifact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sourcepit.osgify.maven.model.maven.MavenArtifact#getFile <em>File</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sourcepit.osgify.maven.model.maven.MavenModelPackage#getMavenArtifact()
 * @model
 * @generated
 */
public interface MavenArtifact extends VersionedIdentifiable, Classified
{
   /**
    * Returns the value of the '<em><b>File</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>File</em>' attribute isn't clear, there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>File</em>' attribute.
    * @see #setFile(File)
    * @see org.sourcepit.osgify.maven.model.maven.MavenModelPackage#getMavenArtifact_File()
    * @model dataType="org.sourcepit.modeling.common.EJavaFile"
    * @generated
    */
   File getFile();

   /**
    * Sets the value of the '{@link org.sourcepit.osgify.maven.model.maven.MavenArtifact#getFile <em>File</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>File</em>' attribute.
    * @see #getFile()
    * @generated
    */
   void setFile(File value);

} // MavenArtifact
