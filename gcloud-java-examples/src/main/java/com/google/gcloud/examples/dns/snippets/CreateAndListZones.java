/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * EDITING INSTRUCTIONS
 * This file is referenced in README's and javadoc. Any change to this file should be reflected in
 * the project's README's and package-info.java.
 */

package com.google.gcloud.examples.dns.snippets;

import com.google.gcloud.dns.Dns;
import com.google.gcloud.dns.DnsOptions;
import com.google.gcloud.dns.Zone;
import com.google.gcloud.dns.ZoneInfo;

import java.util.Iterator;

/**
 * A snippet for Google Cloud DNS showing how to create a zone and list all zones in the project.
 * You will need to change the {@code domainName} to a domain name, the ownership of which you
 * should verify with Google.
 */
public class CreateAndListZones {

  public static void main(String... args) {
    // Create a service object
    // The project ID and credentials will be inferred from the environment.
    Dns dns = DnsOptions.defaultInstance().service();

    // Create a zone metadata object
    String zoneName = "my_unique_zone"; // Change this zone name which is unique within your project
    String domainName = "someexampledomain.com."; // Change this to a domain which you own
    String description = "This is a gcloud-java-dns sample zone.";
    ZoneInfo zoneInfo = ZoneInfo.of(zoneName, domainName, description);

    // Create zone in Google Cloud DNS
    Zone createdZone = dns.create(zoneInfo);
    System.out.printf("Zone was created and assigned ID %s.%n", createdZone.id());

    // Now list all the zones within this project
    Iterator<Zone> zoneIterator = dns.listZones().iterateAll();
    int counter = 1;
    while (zoneIterator.hasNext()) {
      System.out.printf("#%d.: %s%n%n", counter, zoneIterator.next().toString());
      counter++;
    }
  }
}
