package orbs.models

import java.awt.geom.Point2D
import spock.lang.Specification

class MageSpec extends Specification {

    void "should create new mage without any problems"() {
        when:
        Point2D.Double coordinates = new Point2D.Double(42, 69)
        Mage bot = new Mage('my_test_mage', coordinates)

        then:
        bot.name == 'my_test_mage'
    }
}
