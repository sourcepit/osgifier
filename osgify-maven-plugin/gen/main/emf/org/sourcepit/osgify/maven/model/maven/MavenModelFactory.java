/**
 * <copyright>
 * </copyright>
 * 
 * $Id$
 */

package org.sourcepit.osgify.maven.model.maven;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.sourcepit.osgify.maven.model.maven.MavenModelPackage
 * @generated
 */
public interface MavenModelFactory extends EFactory
{
   /**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   MavenModelFactory eINSTANCE = org.sourcepit.osgify.maven.model.maven.impl.MavenModelFactoryImpl.init();

   /**
    * Returns a new object of class '<em>Maven Artifact</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Maven Artifact</em>'.
    * @generated
    */
   MavenArtifact createMavenArtifact();

   /**
    * Returns a new object of class '<em>Maven Dependency</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Maven Dependency</em>'.
    * @generated
    */
   MavenDependency createMavenDependency();

   /**
    * Returns a new object of class '<em>Maven Project</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Maven Project</em>'.
    * @generated
    */
   MavenProject createMavenProject();

   /**
    * Returns the package supported by this factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the package supported by this factory.
    * @generated
    */
   MavenModelPackage getMavenModelPackage();

} // MavenModelFactory
